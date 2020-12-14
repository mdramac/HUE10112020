package application.controller;


import application.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class StatusController {

    public ListView<Status> statiListView;
    public TextField statiTextField;
    ObservableList<Status> list = FXCollections.observableArrayList();
    private int number = 0;

    private Status selectedStati = null;

    public void initialize() {
        statiListView.setItems(Status.load("stati.csv"));
    }




    public void listclicked(MouseEvent mouseEvent) {
        Status selected = statiListView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedStati = selected;

            statiTextField.setText(selected.name);
        }
    }

    public void newClicked(ActionEvent actionEvent) {
        statiTextField.clear();

        this.selectedStati = null;
    }

    public void saveClicked(ActionEvent actionEvent) {
        if (this.selectedStati != null) {


            selectedStati.name = statiTextField.getText();

            statiListView.refresh();
        } else {
            Status a = new Status();

            a.name = statiTextField.getText();
            a.number = Integer.toString(number + 1);

            list.add(a);

        }

        fileWriter();
    }

    private void fileWriter() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("stati.csv"));

            for (Status a : list) {
                bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteClicked(ActionEvent actionEvent) {
        Status selected = statiListView.getSelectionModel().getSelectedItem();

        list.remove(selected);
        statiListView.refresh();

        fileWriter();
    }
}
