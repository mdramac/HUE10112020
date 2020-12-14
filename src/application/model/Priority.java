package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Priority {
    public String number;
    public String name;

    @Override
    public String toString() { return number + " - " +  name;}

    public String newCSVLine (){
        return number + "\";\"" + name + "\";\"";
    }

    public  static ObservableList<Priority> load(String file) {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Priority a = new Priority();

                    String[] words = s.split(";");
                    a.number = words[0];
                    a.name = words[1];

                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }
        return list;
    }
}
