package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Profilepage extends UserHome implements Initializable{
    @FXML
    TextArea name, mobile, address, age, mail, gender;
    @FXML
    Button backHome;

    public void backHome(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String data[] = new String[0];
        try {
            data = Main.dataInputStream.readUTF().split("#");
        } catch (IOException e) {
            e.printStackTrace();
        }
        name.setText(data[0]);
        mobile.setText(data[1]);
        mail.setText(data[2]);
        age.setText(data[3]);
        address.setText(data[4]);
        gender.setText(data[5]);
    }
}
