package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Status {
    public int id;
    public  String number;
    public String name;


    @Override
    public String toString() { return number + " - " +  name;}

    public String newCSVLine (){
        return number + "\";\"" + name + "\";\"";
    }

    public Status(String number, String name) {
        this.number = number;
        this.name = name;
    }

    /** public  static ObservableList<Status> load(String file) {
        ObservableList<Status> list = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Status a = new Status();

                    String[] words = s.split(";");
                    a.number = words[0];
                    a.id = Integer.parseInt(a.number);
                    a.name = words[1];


                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }
    return  list;
    */


   public static  Status getById(int id) {
       Status obj = null;
       try {
           Connection connection = AccessDb.getConnection();

           Statement statement = null;
           statement = connection.createStatement();
           ResultSet result = statement.executeQuery("SELECT * FROM stati WHERE status_id = " + id);

           if(result.next()) {
               obj = new Status(result.getString("status_id"),result.getString("name"));

           }

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }

       return obj;
   }

   public void delete(){
       try {
           Connection connection = AccessDb.getConnection();

           Statement statement = null;
           statement = connection.createStatement();
           statement.executeUpdate("DELETE FROM stati WHERE status_id = " + number);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }

    public void update(){
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE stati SET name = ? WHERE status_id = " +number);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

        public  static ObservableList<Status> load() {
            ObservableList<Status> list = FXCollections.observableArrayList();

            try {
                Connection connection = AccessDb.getConnection();

                Statement statement = null;
                statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM stati");

                while (result.next()){
                    Status p = new Status(Integer.toString(result.getInt("status_id")),result.getString("name"));

                    list.add(p);
                }
            } catch (SQLException throwables) {
            }

            return list;
        }


    }

