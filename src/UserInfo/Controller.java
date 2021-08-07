package UserInfo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Client;
import sample.FileWorks;
import sample.Main;

import static sample.Main.ClientList;

public class Controller {

    @FXML
    private Label UserIdLabel;

    @FXML
    private TextField UserIdText;

    @FXML
    private Label NameLabel;

    @FXML
    private TextField NameText;

    @FXML
    private Label EmailLabel;

    @FXML
    private TextField MailText;

    @FXML
    private Button SubBut;

    private Client client;//=new Client();


    public void submit(ActionEvent actionEvent)
    {
        if(!UserIdText.getText().isEmpty())
        {
            //check if it exists or not
            //if exists then decline change, else
            client.setUserName(UserIdText.getText());
        }
        if(!NameText.getText().isEmpty())
        {
            client.setFullname(NameText.getText());
        }
        if(!MailText.getText().isEmpty())
        {
            if(MailText.getText().indexOf("@")!=-1)
            {
                client.setEmail(MailText.getText());
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Mail Address");
                alert.setHeaderText("Wrong Mail Address");
                alert.setContentText("Please enter correct mail address");
                alert.showAndWait();
            }
        }
       if(UserIdText.getText().isEmpty() && NameText.getText().isEmpty() &&NameText.getText().isEmpty())
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            //alert.setTitle("Wrong Mail Address");
            alert.setHeaderText("No Change");
            alert.setContentText("You Did not make any change");
            alert.showAndWait();
        }
       else
       {

           try {
               for (Client cl : ClientList){
                   if(cl.getUserName().equals(client.getUserName()) || cl.getFullname().equals(client.getFullname())||cl.getEmail().equals(client.getEmail())) {
                      // cl.setPass(client.getPass());
                       cl.setFullname(client.getFullname());
                       cl.setUserName(client.getUserName());
                       cl.setEmail(client.getEmail());
                       break;
                   }
                   }
               FileWorks.writeToFile(ClientList);
           } catch (Exception e) {
               e.printStackTrace();
           }
           Alert alert=new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information change successfully");
           alert.setHeaderText("Information change successfully");
           alert.setContentText("Your information has changed successfully.");
           alert.showAndWait();
       }

        System.out.println(client.getUserName()+" "+client.getFullname()+" "+client.getEmail()+"pass: "+client.getPass());
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
