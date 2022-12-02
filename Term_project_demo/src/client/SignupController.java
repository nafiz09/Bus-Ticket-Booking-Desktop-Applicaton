package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SignupController {
    @FXML
    TextField username, name, mail, age, add, no;
    @FXML
    PasswordField password;
    @FXML
    MenuButton menuButton;
    @FXML
    MenuItem male, female;
    @FXML
    Button signUp, logIn;

    String filename = "User_names.txt";

    public void actionSignupButton (ActionEvent event) throws IOException {

        HashMap<String, String> userData = new HashMap<>();
        userData.put("filename", filename);
        userData.put("username", username.getText());
        userData.put("password", password.getText());
        userData.put("name", name.getText());
        userData.put("number", no.getText());
        userData.put("mail", mail.getText());
        userData.put("age", age.getText());
        userData.put("add", add.getText());
        userData.put("gender", menuButton.getText());

        Main.dataOutputStream.writeUTF("signup");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(Main.socket.getOutputStream());
        objectOutputStream.writeObject(userData);
        objectOutputStream.flush();

        String str = Main.dataInputStream.readUTF();

        if (str.equals("false"))
            username.setText("User name already used");
        else if (str.equals("true")){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userHome.fxml"));
            Parent root = loader.load();

            UserHome userHome = loader.getController();
            userHome.loggedIn.setText(username.getText());

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }

    public void actionMaleButton(ActionEvent event) {
        menuButton.setText("Male");
    }

    public void actionFemaleButton(ActionEvent event) {
        menuButton.setText("Female");
    }

    @FXML
    void loginpage(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }


}
