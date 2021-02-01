package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Department {
    public String number;
    public String name;
    public int id;

    @Override
    public String toString() { return number + " - " +  name;}

    public String newCSVLine (){
        return number + ";" + name + ";\n";
    }

    public Department(String number, String name) {
        this.number = number;
        this.name = name;
    }

    /**public  static ObservableList<Department> load(String file) {
        ObservableList<Department> list = FXCollections.observableArrayList();

        String row;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            try {
                while ((row = br.readLine()) != null) {
                    String[] data = row.split(";");
                    Department b = new Department();

                    b.number = data[0];
                    b.id = Integer.parseInt(b.number);
                    b.name = data[1];
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

        return list;
    }*/



    public static  Department getById(int id) {
        Department obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM department WHERE department_id = " + id);

            if(result.next()) {
                obj = new Department(result.getString("department_id"),result.getString("name"));

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
            statement.executeQuery("DELETE FROM Departments WHERE department");


        } catch (SQLException throwables) {
        }
    }

    public void update(){
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE Departments SET name = ? WHERE department = " +number);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    public  static ObservableList<Department> load() {
        ObservableList<Department> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Departments");

            while (result.next()){
                Department p = new Department(Integer.toString(result.getInt("department")),result.getString("name"));

                list.add(p);
            }
        } catch (SQLException throwables) {
        }

        return list;
    }
}
