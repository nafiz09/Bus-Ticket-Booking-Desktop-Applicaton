package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHome implements Initializable {
    @FXML
    Button home;
    @FXML
    Button logOut;
    @FXML
    Button profile;
    @FXML
    Button prevPurchase;
    @FXML
    Button search;
    @FXML
    Label loggedIn;
    @FXML
    BorderPane borderPane;
    @FXML
    ComboBox<String> startCombo;
    @FXML
    ComboBox<String> destiCombo;
    @FXML
    DatePicker datePicker;

    public static VehicleList vl;
    public static ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            home.setDisable(true);

            ObservableList<String> leaveFrom = FXCollections.observableArrayList();


            Main.dataOutputStream.writeUTF("loadcombo1");
            String data[];
            data = Main.dataInputStream.readUTF().split("#");
            for (int i=0; i<data.length; i++) {
                leaveFrom.add(data[i]);
            }
            startCombo.getItems().setAll(leaveFrom);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionHome (ActionEvent event) throws IOException {
        profile.setDisable(false);
        home.setDisable(true);
    }

    public void onActionlogout (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void OnActionProfile (ActionEvent event) throws IOException {
        Main.dataOutputStream.writeUTF("setprofile#"+loggedIn.getText()+".txt");
        Parent root = FXMLLoader.load(getClass().getResource("profilepage.fxml"));

        home.setDisable(false);
        profile.setDisable(true);
        borderPane.setCenter(root);

    }

    public void onActionstartCombo (ActionEvent event) throws IOException {
        ObservableList<String> goingTo = FXCollections.observableArrayList();
        Main.dataOutputStream.writeUTF("loadcombo2#"+startCombo.getValue());
        String data[];
        data = Main.dataInputStream.readUTF().split("#");
        for (int i=0; i<data.length; i++) {
            goingTo.add(data[i]);
        }
        destiCombo.getItems().setAll(goingTo);

    }

    public void onActionsearch (ActionEvent event) throws IOException {
        if (datePicker.getValue()!=null&&startCombo.getValue()!=null&&destiCombo.getValue()!=null) {
            vehicleList.clear();
            String start = startCombo.getValue();
            String destination = destiCombo.getValue();
            String day = datePicker.getValue().getDayOfWeek().toString().toLowerCase();

            String str = "search#"+day+"#"+start+"#"+destination+"#";
            Main.dataOutputStream.writeUTF(str);

            String vehicleData = Main.dataInputStream.readUTF();
            while(!vehicleData.equals("end")) {
                Vehicle vehicle = new Vehicle(vehicleData);
                vehicleList.add(vehicle);
                vehicleData = Main.dataInputStream.readUTF();
            }

            Parent root = FXMLLoader.load(getClass().getResource("vehicleList.fxml"));
            borderPane.setCenter(root);
            profile.setDisable(false);
            home.setDisable(false);

        }
    }
}
