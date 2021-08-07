package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


import java.io.IOException;

public class AdminLoginController  extends Main {
    @FXML
    private PasswordField password;

    @FXML
    private TextField adminname;


    @FXML
    private ImageView imageview;

    @FXML
    private Label label;

    Admin admin = new Admin();

    int isAvail = 0;

    @FXML
    void LoginAction(ActionEvent event) {
        String name = adminname.getText();
        admin.setName(name);
        String pass = password.getText();
        admin.setPassword(pass);
        Admin admin = getAdmin();

        if(admin!=null) {
            try {
                main.getNetworkUtil().write(admin);
                main.getNetworkUtil().write("Admin logged in Successfully");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (name.isEmpty() || pass.isEmpty()) {
            label.setVisible(true);
            label.setText("Please Fill up All the Information");
        }
    }

    @FXML
    public void BacktoMain() throws IOException {

        main.ShowWelcome();
    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public Admin getAdmin() {
        int index = -1;
        for (int i = 0; i < AdminList.size(); i++) {
            if (AdminList.get(i).getName().equalsIgnoreCase(admin.getName())) {
                if (AdminList.get(i).getPassword().equals(admin.getPassword())) {
                    System.out.println("Login Successful!!");
                    adminname.clear();
                    password.clear();
                    label.setVisible(false);
                    index = i;
                } else {
                    label.setVisible(true);
                    label.setText("Wrong Password");
                    break;
                }
            }




        }
            if (index != -1)
                return AdminList.get(index);
            else
                return null;



    }

}
