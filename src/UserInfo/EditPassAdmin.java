package UserInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.*;

import static sample.Main.AdminList;
import static sample.Main.ClientList;

public class EditPassAdmin {

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
    @FXML
    private Button SubBut1;

    private Main main;
    private Admin admin;

    public void setAdmin(Admin admin){this.admin = admin;}
    @FXML
    void submit(ActionEvent event) throws Exception {
        //System.out.println(client.getUserName()+ client.getPass());
        // System.out.println(admin.getName()+","+admin.getPassword());
        if(!(Pas.getText().equals(admin.getPassword())))
        {
            //alert wrong pass input
            //SubBut.setV
            Pas.setText(null);
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
                admin.setPassword(newPas.getText());

                for (Admin ad : AdminList){
                    if(ad.getName().equals(admin.getName())){
                        ad.setPassword(admin.getPassword());
                        break;
                    }
                }
                AdminFile.writefile(AdminList);
                Reset(3);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password change successfully");
                alert.setHeaderText("Password change successfully");
                alert.setContentText("Your password has changed successfully.");
                alert.showAndWait();
            }
            else
            {
                Reset(2);
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Password Mismatch");
                alert.setHeaderText("Password Mismatch");
                alert.setContentText("Your password does not match. Please enter correct password.");
                alert.showAndWait();
            }
        }

    }

    private void Reset(int n)
    {
        if(n==2)
        {
            newPas.setText(null);
            newPas2.setText(null);
        }
        else
        {
            newPas.setText(null);
            newPas2.setText(null);
            Pas.setText(null);
        }
    }
}
