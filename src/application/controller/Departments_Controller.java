package application.controller;

import application.model.Department;
import application.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;

public class Departments_Controller {

    public Button save;
    public Button cancel;
    public ListView<Department> listViewDepartments;
    private Department selectedDep = null;
    private int number = 0;


    public static final ObservableList<Department> departmentList =
            FXCollections.observableArrayList();

    public File datei = new File("departments.csv");
    public TextField departmentTextfield;

    public void initialize() {

        departmentList.clear();

        String row;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(datei));
            try {
                while ((row = br.readLine()) != null) {
                    String[] data = row.split(";");
                    Department b = new Department();

                    b.number = data[0];
                    b.name = data[1];
                    departmentList.add(b);
                }
            } finally {
                if (br != null) {
                    br.close();
                }
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        listViewDepartments.setItems(departmentList);
    }


    public void saveButtonClicked(ActionEvent actionEvent) {
        if (this.selectedDep != null) {


            selectedDep.name = departmentTextfield.getText();

            listViewDepartments.refresh();
        } else {
            Department a = new Department();

            a.name = departmentTextfield.getText();
            a.number = Integer.toString(number + 1);

            departmentList.add(a);

        }

        fileWriter();
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    private void fileWriter() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("departments.csv"));

            for (Department a : departmentList) {
                bw.write(a.newCSVLine());
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listClicked(MouseEvent mouseEvent) {
        Department selected = listViewDepartments.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedDep = selected;

            departmentTextfield.setText(selected.name);
        }
    }

    public void newDepartmentClicked(ActionEvent actionEvent) {
        departmentTextfield.clear();

        this.selectedDep = null;
    }
}
