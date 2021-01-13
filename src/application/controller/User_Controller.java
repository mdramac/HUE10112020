package application.controller;

import application.model.Department;
import application.model.Priority;
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

import java.io.*;

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
    public ComboBox<Department> userDepartment;
    private User selectedUser = null;
    private Department d;



    public void initialize() {

        listViewUser.setItems(User.load("users.csv"));
        userDepartment.setItems(Department.load("departments.csv"));

    }


    public void saveClicked(ActionEvent actionEvent) {
        if (this.selectedUser != null) {


            selectedUser.name = username.getText();

            listViewUser.refresh();
        } else {
            User b = new User();

            b.number = Integer.parseInt(username.getText());
            b.titel = userTitel.getText();
            b.name = username.getText();
            b.strase = userStreet.getText();
            b.plz = Integer.parseInt(userPlz.getText());
            b.ort = userOrt.getText();

            //b.abteilung = Integer.parseInt(userDepartment.getItems());

            list.add(b);

        }

        fileWriter();
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

    private void fileWriter() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));

            for (User a : list) {
                bw.write(a.newCSVLine());
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
