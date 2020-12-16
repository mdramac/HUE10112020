package application.controller;

import application.MyFXMLLoader;
import application.model.ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {
    public ListView<StatusController> statiList;
    public ListView<ticket> listVIew;
    public AnchorPane contentPane;

    private ControllerTickets active = null;

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
}
