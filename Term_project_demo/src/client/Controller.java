package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML MenuButton menuButton;
    @FXML MenuItem userButton;
    @FXML MenuItem adminButton;
    @FXML TextField textField;
    @FXML PasswordField passwordField;
    @FXML Button loginButton;
    @FXML Button signupButton;
    @FXML Label label;
    String output;


    public void actionUserButton(ActionEvent event) {
        menuButton.setText("User");
    }

    public void actionAdminButton(ActionEvent event) {
        menuButton.setText("Admin");
    }

    public void actionLoginButton (ActionEvent event) throws IOException {
        boolean loginSuccess;
        if (menuButton.getText().equals("User"))
            loginSuccess = checkLogin("User_names.txt");
        else
            loginSuccess = checkLogin("Admin_names.txt");

        if(loginSuccess && menuButton.getText().equals("User")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userHome.fxml"));
            Parent root = loader.load();

            UserHome userHome = loader.getController();
            userHome.loggedIn.setText(textField.getText());

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

        }
        if(loginSuccess && menuButton.getText().equals("Admin")) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("adminUI.fxml"));
            Parent root = loader.load();

            AdminUI adminUI = loader.getController();
            adminUI.loggedIn.setText(textField.getText());

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

        }
        else {
            label.setAlignment(Pos.CENTER);
            label.setText("Invalid user name or password");
        }
    }

    public void actionSignupButton (ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public boolean checkLogin(String filename) throws IOException{

        output = "login#"+filename+"#"+textField.getText()+"#"+ passwordField.getText();
        Main.dataOutputStream.writeUTF(output);
        Main.dataOutputStream.flush();

        if (Main.dataInputStream.readUTF().equals("success"))
            return true;

        return false;
    }


}
