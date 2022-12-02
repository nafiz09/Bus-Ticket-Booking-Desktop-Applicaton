package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Addbus {

    @FXML
    TextField startCounter, endCounter, agencyName, coachNo, time, startPoint, fare, endPoint, coachType, arrivalTime;
    @FXML
    Button addBus;

    public void onActionaddBus(ActionEvent event) throws IOException {
        String str = "addbus#"+startPoint.getText()+"#"+endPoint.getText()+"#";
        str += agencyName.getText()+"#"+coachNo.getText()+"#"+time.getText()+"#";
        str += startCounter.getText()+"#"+endCounter.getText()+"#"+ coachType.getText()+"#";
        str += fare.getText()+"#"+arrivalTime.getText()+"#";

        Main.dataOutputStream.writeUTF(str);
    }
}
