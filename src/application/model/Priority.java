package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Priority {
    public String number;
    public String name;

    @Override
    public String toString() { return number + " - " +  name;}

    public String newCSVLine (){
        return number + "\";\"" + name + "\";\"";
    }

    public void delete(){
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM priorities WHERE priorties_id = " + number);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE priorities SET name = ? WHERE priorties_id = " +number);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    public static  Priority getById(int id) {
        Priority obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities WHERE priotity_id = " + id);

            if(result.next()) {
                obj = new Priority(result.getString("priotity_id"),result.getString("name"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }

    public Priority(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public  static ObservableList<Priority> load() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities");

            while (result.next()){
                Priority p = new Priority(Integer.toString(result.getInt("priorties_id")),result.getString("name"));

                list.add(p);
            }
        } catch (SQLException throwables) {
        }

        return list;
    }
}
