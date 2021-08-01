package Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    private Button logoutBut;

    @FXML
    private Button DashBut;

    @FXML
    private Button complaintBut;

    @FXML
    private Button historyBut;

    @FXML
    private Button setBut;

    @FXML
    private Button infoBut;

    @FXML
    private Button pasChangeBut;

    @FXML
    private VBox vbox;
    private Parent parent;

    private Main main;

    @FXML
    void changePass(ActionEvent event)
    {
        try {
            parent = FXMLLoader.load(getClass().getResource("/UserInfo/EditPass.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(parent);
    }

    @FXML
    void logout(ActionEvent event)
    {
        try {
            main.showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showComplaint(ActionEvent event) {

    }

    @FXML
    void showDash(ActionEvent event) {

    }

    @FXML
    void showEditInfo(ActionEvent event)
    {
        try {
            parent = FXMLLoader.load(getClass().getResource("/UserInfo/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        vbox.getChildren().removeAll();
        vbox.getChildren().setAll(parent);
    }

    @FXML
    void showHistory(ActionEvent event) {

    }

    @FXML
    void showSettings(ActionEvent event)
    {
        infoBut.setVisible(true);
        pasChangeBut.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        infoBut.setVisible(false);
        pasChangeBut.setVisible(false);


    }

    public void setMain(Main main) {
        this.main = main;
    }
}

