package application.controller;

import application.model.Status;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class User_Controller {
    public ListView<User> listViewUser;

    public Button save;
    public Button cancel;

    public static final ObservableList<User> list = FXCollections.observableArrayList();
    public TextField username;
    public TextField userTitel;
    public TextField userStreet;
    public TextField userPlz;
    public TextField userOrt;
    public ComboBox userDepartment;
    private User selectedUser = null;

    public File file = new File("users.csv");

    public void initialize() {

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

        listViewUser.setItems(list);
    }

    public void saveClicked(ActionEvent actionEvent) {
    }

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    public void listClicked(MouseEvent mouseEvent) {
        User selected = listViewUser.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedUser = selected;

            username.setText(selected.name);
            userTitel.setText(selected.titel);
            userOrt.setText(selected.ort);
            userPlz.setText(String.valueOf(selected.plz));
            userStreet.setText(selected.strase);
            //userDepartment.setPlaceholder(String.valueOf(selected.abteilung);
        }
    }
}
