package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ticket {
    public String number;
    public String name;
    public String ticketDes;
    public String status;
    public String priority;

    @Override
    public String toString() { return number + " - " +  name;}

    public String newCSVLine (){
        return number + ";" + name + ";\n";
    }

    public  static ObservableList<ticket> load(String file) {
        ObservableList<ticket> list = FXCollections.observableArrayList();

        String row;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            try {
                while ((row = br.readLine()) != null) {
                    String[] data = row.split(";");
                    ticket b = new ticket();

                    b.number = data[0];
                    b.name = data[1];
                    b.ticketDes = data[2];
                    b.status = data[3];
                    b.priority = data[4];

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
