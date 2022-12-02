package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VehicleList implements Initializable {
    @FXML TableView tableView;
    @FXML TableColumn <Vehicle, String> agencyName;
    @FXML TableColumn <Vehicle, String> coachNo;
    @FXML TableColumn <Vehicle, String> time;
    @FXML TableColumn <Vehicle, String> startingCounter;
    @FXML TableColumn <Vehicle, String> endCounter;
    @FXML TableColumn <Vehicle, String> coachType;
    @FXML TableColumn <Vehicle, String> fare;
    @FXML TableColumn <Vehicle, String> arrivalTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        agencyName.setCellValueFactory(new PropertyValueFactory<>("Agency"));
        coachNo.setCellValueFactory(new PropertyValueFactory<>("CoachNo"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        startingCounter.setCellValueFactory(new PropertyValueFactory<>("StartingCounter"));
        endCounter.setCellValueFactory(new PropertyValueFactory<>("EndCounter"));
        coachType.setCellValueFactory(new PropertyValueFactory<>("CoachType"));
        fare.setCellValueFactory(new PropertyValueFactory<>("Fare"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("ArrivalTime"));

        tableView.setItems(UserHome.vehicleList);

        tableView.getSortOrder().add(time);

        UserHome.vl = this;

    }

    public void onMousePressed(MouseEvent mouseEvent) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("buslayout.fxml"));
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();

    }
}
