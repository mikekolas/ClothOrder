package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();


    public TextField txtUser;
    public Button btnLogin;
    public Button btnExit;
    public Label lblStatus;
    public PasswordField txtPass;

    public void clicked(Event event) throws Exception {
        if(txtUser.getText().equals("admin") && txtPass.getText().equals("root"))
        {
            System.out.println("Welcome");
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("Order.fxml"));
            primaryStage.setTitle("Cloth Orders");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
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
