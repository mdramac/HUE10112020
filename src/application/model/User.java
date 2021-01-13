package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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


    public  static ObservableList<User> load(String file) {
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

                    b.number = Integer.parseInt(data[0]);
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
    }

}
