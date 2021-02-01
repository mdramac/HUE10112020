package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Controller {
    public ListView<StatusController> statiList;
    public ListView<ticket> listVIew;
    public AnchorPane contentPane;
    public ComboBox<Priority> filterPriority;
    public ComboBox<Status> filterStatus;
    public TextField filterNameTextField;
    ObservableList<ticket> list = FXCollections.observableArrayList();
    ArrayList<ticket> set = new ArrayList<>();
    private ControllerTickets active = null;

    public void initialize() {
        listVIew.setItems(ticket.load("todo.csv"));
        set.addAll(ticket.load("todo.csv" ));
        filterStatus.setItems(Status.load());
        filterPriority.setItems(Priority.load());


    }

    public void editStaticlicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/stati.fxml", "Stati bearbeiten");
    }

    public void editPrioritiesclicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priorities.fxml" , "Prioritäten bearbeiten");
    }

    public void editUserscklicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/user.fxml" , "Benutzer bearbeiten");
    }

    public void editDepartmentClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/departments.fxml" , "Abteilung bearbeiten");
    }

    public void ticket_listViewClicked(MouseEvent mouseEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root=  loader.loadFXML("view/ticket.fxml");
        contentPane.getChildren().add(root);


        active = (ControllerTickets) loader.getController();
        active.setTicket(listVIew.getSelectionModel().getSelectedItem());
    }

    public void newClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");

        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        contentPane.getChildren().add(root);

        ControllerTickets controller = (ControllerTickets) loader.getController();
        controller.setTicket(null);
    }

    public void delteClicked(ActionEvent actionEvent) {
        //laden des Tickets
        //Entfernen aus Listview
        //Datei aktualisieren
    }

    public void saveClicked(ActionEvent actionEvent) {
        //Wenn Ticker new -> Lden des Tickets unf hinzufügen zur Liste!
        //Datei aktualisieren
    }

    public void filterTyped(KeyEvent keyEvent) {
        list.clear();
        for (ticket a : set) {
            if (a.ticketDes.contains(filterNameTextField.getCharacters())) {
                list.add(a);
            } else if (a.name.contains(filterNameTextField.getCharacters())) {
                list.add(a);
            }
        }
        listVIew.setItems(list);
    }

    public void filterStatusClicked(ActionEvent actionEvent) {
        list.clear();
        for (ticket a : set) {
            if (a.status.equals(filterStatus.getSelectionModel().getSelectedItem())) {
                list.add(a);
            }
        }
        listVIew.setItems(list);
    }

    public void filterPriorityClicked(ActionEvent actionEvent) {
        list.clear();

        for (ticket a : set) {
            if (a.priority.equals(filterPriority.getSelectionModel().getSelectedItem())) {
                list.add(a);
            }
        }
        listVIew.setItems(list);
    }
}
