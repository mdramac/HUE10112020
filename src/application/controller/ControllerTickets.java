package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.User;
import application.model.ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.File;

public class ControllerTickets {
    public ListView<ticket> lisTicket;
    public TextField textfieldName;
    public ComboBox<Status> boxStati;
    public ComboBox<Priority> boxPriority;
    public Button saveButton;
    public Button deleteButton;
    public TextArea textArea;
    private  ticket ticket;
    private Status s;
    private Priority p;
    public static final ObservableList<ticket> ticketList =
            FXCollections.observableArrayList();
    public File datei = new File("tickets.csv");



    public void setTicket(ticket t){
        this.ticket = t;
        textfieldName.setText((ticket.name));
        textArea.setText(ticket.ticketDes);
        boxStati.setItems(Status.load("stati.csv"));
        boxPriority.setItems(Priority.load());

        for (Status s : boxStati.getItems()){
            if(s.id == t.id){
                boxStati.getSelectionModel().select(s);
                break;
            }
        }

        for (Priority p : boxPriority.getItems()){
            if(p.id == t.ide){
                boxPriority.getSelectionModel().select(p);
                break;
            }
        }

    }

    public  ticket getTicket(){
        /**
         * aktualisieren der Tticket-Daten
         */
        ticket.name = textfieldName.getText();
        ticket.ticketDes = textArea.getText();

        return  ticket;
    }
}
