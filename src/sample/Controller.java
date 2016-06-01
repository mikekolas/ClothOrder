package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public LoginModel loginModel = new LoginModel();


    public TextField txtUser;
    public Button btnLogin;
    public Button btnExit;
    public Label lblStatus;
    public PasswordField txtPass;

    public void clicked(Event event) {
        if(txtUser.getText().equals("admin") && txtPass.getText().equals("root"))
        {
            System.out.println("Welcome");
        }
    }

    public void appExit(Event event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(loginModel.isDbConnected())
        {
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Connected");
        }
        else
        {
            lblStatus.setTextFill(Color.RED);
            lblStatus.setText("Not Connected");
        }
    }
}
