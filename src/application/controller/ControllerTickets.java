package application.controller;

import application.model.Department;
import application.model.ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.File;

public class ControllerTickets {
    public TextField textfieldName;
    public ComboBox boxStati;
    public ComboBox boxPriority;
    public Button saveButton;
    public Button deleteButton;
    public TextArea textArea;
    public static final ObservableList<ticket> ticketList =
            FXCollections.observableArrayList();
    public File datei = new File("tickets.csv");

    public void initialize() {
        ticketList.setI();

    }
    public void buttonClicked(ActionEvent actionEvent) {

    }

    public void saveClicked(ActionEvent actionEvent) {

    }
}
