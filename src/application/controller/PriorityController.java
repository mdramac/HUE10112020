package application.controller;

import application.model.Priority;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class PriorityController {
    public ListView<Priority> priorityListView;
    public TextField priorityTextfield;
    ObservableList<Priority> list = FXCollections.observableArrayList();
    private int number = 0;

    private Priority selectedPriority = null;


    public void initialize() {

        priorityListView.setItems(Priority.load());
    }


    public void listclicked(MouseEvent mouseEvent) {
        Priority selected = priorityListView.getSelectionModel().getSelectedItem();

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
            priorityListView.refresh();
            selectedPriority.update();
        } else {
            Priority a = new Priority();

            a.name = priorityTextfield.getText();
            a.number = Integer.toString(number + 1);

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
        Priority selected = priorityListView.getSelectionModel().getSelectedItem();
        priorityTextfield.clear();
        priorityListView.getItems().remove(selected);
        selected.delete();

    }


}
