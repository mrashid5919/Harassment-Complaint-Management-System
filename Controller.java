package UserInfo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        if(UserIdText.getText()!= null)
        {
            //check if it exists or not
            //if exists then decline change, else
            client.setUserId(UserIdText.getText());
        }
        if(NameText.getText()!=null)
        {
            client.setUserName(NameText.getText());
        }
        if(MailText.getText()!=null)
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
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
