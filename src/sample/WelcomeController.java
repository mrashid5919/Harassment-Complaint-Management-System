package sample;

import javafx.event.ActionEvent;

import java.io.IOException;

public class WelcomeController {
    private Main main;

    public void LoginActionAdmin(ActionEvent event) throws IOException {
        String type="Admin";
        main.getNetworkUtil().write(type);
        main.ShowAdminLogin();
    }

    public void ClientLogin(ActionEvent event) throws IOException {
        String type="Client";
        main.getNetworkUtil().write(type);
        main.showLoginPage();
    }

    public void setMain(Main main){
        this.main = main;
    }
}
