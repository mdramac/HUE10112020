package application.controller;

import application.model.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class PriorityController {
    public ListView<Priority> priorityListview;
    public TextField priorityTextfield;
    ObservableList<Priority> list = FXCollections.observableArrayList();

    private Priority selectedPriority = null;


    public void initialize() {
        load(new File("priorities.csv"));
    }
    public void load(File file) {
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

        priorityListview.setItems(list);
    }
    public void listclicked(MouseEvent mouseEvent) {
        Priority selected = priorityListview.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedPriority = selected;

            priorityTextfield.setText(selected.name);
        }
    }

    public void newClicked(ActionEvent actionEvent) {
        priorityTextfield.clear();

        // lösche die Variable, die den gewählten Artikel
        // beinhaltet
        this.selectedPriority = null;
    }

    public void saveClicked(ActionEvent actionEvent) {
        if (this.selectedPriority != null) {
            // Aktualisiere die Artikeldaten
            // (übernimm die aktuellen Daten von den Textfeldern)
            // und speichere alles in die Datei

            selectedPriority.name = priorityTextfield.getText();

            priorityListview.refresh();
        } else {
            Priority a = new Priority();

            a.name = priorityTextfield.getText();


            list.add(a);
            // erzeuge neuen Artikel, füge ihn in die ListView ein
            // und speichere alles in die Datei
        }

        fileWriter();
    }

    private void fileWriter() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("priorities.csv"));

            for (Priority a : list) {
                bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteClicked(ActionEvent actionEvent) {
        Priority selected = priorityListview.getSelectionModel().getSelectedItem();

        list.remove(selected);
        priorityListview.refresh();

        fileWriter();
    }


}
