package sample;

import Complaints.Complaint;
import Others.LoginDTO;
import Others.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label info;
    @FXML
    private Button loginBut;


    //Client cl;
    public void LoginAction(ActionEvent event)  {
        String Name = username.getText();
        String pass = password.getText();

        if(username.getText().isEmpty()||password.getText().isEmpty()){
            info.setText("Fill up All the Information!!");
        }
        Client cl = getTheClient();
        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setUserName(Name);
        loginDTO.setPassword(pass);
        loginDTO.setClient(cl);

        if(cl!=null) {
            try {
                //System.out.println(loginDTO.getUserName()+"  "+ loginDTO.getPassword());
                /*for(Complaint c: Main.getComplaintList()){
                    if(c.getUserId().equals(cl.getUserName())){
                        cl.addComplaint(c);
                    }
                }*/
                main.getNetworkUtil().write(cl);

            } catch (IOException e) {
                System.out.println(main.getNetworkUtil()+" " +e);
                e.printStackTrace();
            }
        }
            else{
                info.setText("Please Give PROPER info or Register");
            }

           /* else if(username.getText().isEmpty()||password.getText().isEmpty()){
                info.setText("Fill up All the Information!!");
            }*/

    }
    private Client getTheClient() {
        int index=-1;
        for (int i = 0; i < Main.ClientList.size(); i++) {
            if (Main.ClientList.get(i).getUserName().equals(username.getText()) && Main.ClientList.get(i).getPass().equals(password.getText())) {
                System.out.println("Login Successfully!!");
                username.clear();
                password.clear();
               index=i;

                // System.out.println(Name+" "+ pass);


            }
        }
        if(index!=-1)
            return Main.ClientList.get(index);
        else
            return null;
    }

    private Main main ;//= new Main();
    public void setMain(Main main) {
        this.main = main;
    }
}
