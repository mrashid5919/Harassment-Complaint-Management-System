package sample;


import Complaints.SearchCompController;
import UserInfo.EditPass;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private VBox vbox;
    private Parent fxml;
    private Main main;


    @FXML
    void open_register(ActionEvent event) {

        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(0);
        t.play();
        t.setOnFinished((e)->{
            try {
                fxml = FXMLLoader.load(getClass().getResource("Register.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            }catch (IOException exception){

            }
        });
    }

    @FXML
    void open_login(ActionEvent event) {

        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*34);
        t.play();
        t.setOnFinished((e)->{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Login.fxml"));
                Parent root = loader.load();

                // Loading the controller
                LoginController lc=loader.getController();
                lc.setMain(main);
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(root);
            } catch (IOException ae) {
                ae.printStackTrace();
            }

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*34);
        t.play();
        t.setOnFinished((e)->{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Login.fxml"));
                Parent root = loader.load();

                // Loading the controller
                LoginController lc=loader.getController();
                lc.setMain(main);
                //fxml = FXMLLoader.load(getClass().getResource("Login.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(root);
            }catch (IOException exception){
                System.out.println("mara");
            }
        });
    }

    public void setMain(Main main)
    {
        this.main=main;
    }

    public void BackOnAction(ActionEvent event) throws IOException {
        main.ShowWelcome();
    }
}
