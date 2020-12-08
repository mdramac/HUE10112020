package application.controller;

import application.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class Controller {
    public ListView<StatusController> statiList;

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

//wichtig über git arbeite, gleich blaues pfeil drücken
}
