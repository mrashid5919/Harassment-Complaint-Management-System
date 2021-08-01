package UserInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditPass {

    @FXML
    private Label PassLabel;

    @FXML
    private PasswordField Pas;

    @FXML
    private Label newPassLabel;

    @FXML
    private PasswordField newPas;

    @FXML
    private Label newPassLabel2;

    @FXML
    private PasswordField newPas2;

    @FXML
    private Button SubBut;

    private Main main;
    private Client client;//=new Client();

    public void setClient(Client c)
    {
        client =c;
    }
    @FXML
    void submit(ActionEvent event)
    {
        if(Pas.getText()!=client.getPass())
        {
            //alert wrong pass input
            //SubBut.setV
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong Password Input");
            alert.setHeaderText("Wrong Password Input");
            alert.setContentText("You've entered wrong password. Please enter correct Password");
            alert.showAndWait();
        }
        else
        {
            if(newPas.getText().equals(newPas2.getText()))
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password change successfully");
                alert.setHeaderText("Password change successfully");
                alert.setContentText("Your password has changed successfully.");
                alert.showAndWait();
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Mismatch");
                alert.setHeaderText("Password Mismatch");
                alert.setContentText("Your password does not match. Please enter correct password.");
                alert.showAndWait();
            }
        }

    }

}
