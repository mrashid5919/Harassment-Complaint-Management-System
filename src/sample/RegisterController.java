package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RegisterController extends Main implements Initializable {
    @FXML
    private PasswordField password;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;
    @FXML
    private RadioButton others;

    @FXML
    private TextField fullname;

    @FXML
    private PasswordField confirmpassword;


    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private Label match;

    @FXML
    private Label error;

    @FXML
    private Label g;

    private ToggleGroup gender;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        g.setVisible(false);
        gender = new ToggleGroup();
        this.male.setToggleGroup(gender);
        this.female.setToggleGroup(gender);
        this.others.setToggleGroup(gender);
    }

    Client client = new Client();
    @FXML
    void RegisterAction(ActionEvent event) throws Exception {
            String name = fullname.getText();
            client.setFullname(name);
            String usname = username.getText();
            client.setUserName(usname);
            //String usid = userid.getText();
            //client.setUserId(usid);
            String em = email.getText();
            client.setEmail(em);
            if(this.gender.getSelectedToggle().equals(this.male)){
                g.setText("Male");
            }
            else if(this.gender.getSelectedToggle().equals(this.female)) {
                g.setText("Female");
            }
            else{
                g.setText("Others");
            }

            client.setGender(g.getText());
            String pass = password.getText();
            client.setPass(pass);
            String pass2 = confirmpassword.getText();
            int isAvailable = 0;
            if (pass2.equals(pass)) {
               isAvailable = 0;
               match.setText("Matched!!");
               match.setTextFill(Color.rgb(0,255,0));
            } else {
                match.setText("Password is wrong.Type correctly!!");
                match.setTextFill(Color.rgb(255, 0, 0));
                isAvailable = 1;
            }

            for (Client c:ClientList) {
                if(c.getFullname().equals(client.getFullname())){
                    isAvailable = 1;
                    error.setText("Please Change the Name!!");
                }
                if(c.getUserName().equalsIgnoreCase(client.getUserName())){
                    isAvailable = 1;
                    error.setText("Please Change the User Name!!");
                }

                if(c.getFullname().equals(client.getFullname())&&c.getUserName().equals(client.getUserName())){
                    isAvailable = 1;
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!!");
                    alert.setHeaderText("Wrong Info");
                    alert.setContentText("Same client!! If you're registered,please login");
                    alert.showAndWait();

                }
            }
            if(fullname.getText().isEmpty() || username.getText().isEmpty()|| g.getText().isEmpty() || email.getText().isEmpty()||password.getText().isEmpty()||confirmpassword.getText().isEmpty()){
                isAvailable = 1;
                error.setText("Fill up All the Info");
            }
            if(isAvailable == 0) {
                error.setVisible(false);
                match.setVisible(false);
                ClientList.add(client);
                FileWorks.writeToFile(ClientList);
                fullname.clear();
                //userid.clear();
                username.clear();
                email.clear();
                password.clear();
                confirmpassword.clear();
                Parent pane;
                pane = FXMLLoader.load(getClass().getResource("RegisteredSuccess.fxml"));
                Scene scene = new Scene(pane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }

    }
}
