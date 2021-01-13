package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Department {
    public String number;
    public String name;
    public int id;

    @Override
    public String toString() { return number + " - " +  name;}

    public String newCSVLine (){
        return number + ";" + name + ";\n";
    }

    public  static ObservableList<Department> load(String file) {
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
    }
}
