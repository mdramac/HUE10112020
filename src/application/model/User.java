package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class User {
    public int number;
    public String titel = "";
    public String name = "";
    public String strase = "";
    public int plz;
    public String ort = "";
    public String land = "";
    public int abteilung;


    @Override
    public String toString() {
        return number + " - " + name;
    }

    public String newCSVLine (){
        return number + "\";\"" + name + "\";\"";
    }

    public User(int number, String titel, String name, String strase, int plz, String ort, String land, int abteilung) {
        this.number = number;
        this.titel = titel;
        this.name = name;
        this.strase = strase;
        this.plz = plz;
        this.ort = ort;
        this.land = land;
        this.abteilung = abteilung;
    }

    public static  User getById(int id) {
        User obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE user_id = " + id);

            if(result.next()) {
                obj = new User(result.getInt("user_id"),result.getString("name"),result.getString("titel"),result.getString("Street"),result.getString("zip"),result.getString("city"),result.getString("country"),result.getInt("department"));

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
            statement.executeUpdate("DELETE FROM users WHERE user_id = " + number);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE users SET name = ? WHERE user_id = " +number);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    public  static ObservableList<User> load() {
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while (result.next()){
                User p = new User(result.getInt("user_id"),result.getString("name"),result.getString("titel"),result.getString("Street"),result.getInt("zip"),result.getString("city"), result.getString("country"),result.getInt("department"));
                p.number = result.getInt("user_id");
                p.name = result.getString("name");
                p.titel = result.getString("titel");
                p.strase = result.getString("Street");
                p.plz = result.getInt("zip");
                p.ort=result.getString("city");
                p.land = result.getString("country");
                p.abteilung=result.getInt("department");




                list.add(p);
            }
        } catch (SQLException throwables) {
        }

        return list;
    }

    /**public  static ObservableList<User> load(File file) {
        ObservableList<User> list = FXCollections.observableArrayList();
        list.clear();

        String row;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            try {
                while ((row = br.readLine()) != null) {
                    String[] data = row.split(";");
                    User b = new User();

                    //b.number = Integer.parseInt(data[0]);
                    b.titel = data[1];
                    b.name = data[2];
                    b.strase = data[3];
                    b.plz = Integer.parseInt(data[4]);
                    b.ort = data[5];
                    b.abteilung = Integer.parseInt(data[6]);

                    list.add(b);
                }
            } finally {
                if (br != null) {
                    br.close();
                }
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        return  list;
    }*/

}
