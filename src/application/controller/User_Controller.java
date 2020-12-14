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


    public void initialize() {
        listViewUser.setItems(User.load("users.csv"));
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
