package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminUI {
    @FXML
    Label loggedIn;
    @FXML
    Button addBus;
    @FXML
    Button logOut;
    @FXML
    BorderPane borderPane;

    public void onActionAddBus(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addbus.fxml"));
        borderPane.setCenter(root);
    }

    public void onActionlogout (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
