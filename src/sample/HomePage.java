package sample;

import Complaints.DashController;
import Complaints.HistoryController;
import UserInfo.EditPass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    private Button logoutBut;

    @FXML
    private Label usename;

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
    @FXML
    private PieChart pieChart;

    @FXML
    private Label pLabel;

    @FXML
    private Label sLabel;

    private Main main;
    private Client client;

    @FXML
    void changePass(ActionEvent event)
    {
        try
        {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/UserInfo/EditPass.fxml"));
            parent = loader.load();

            EditPass ep=loader.getController();
            ep.setClient(client);
            System.out.println(client.getUserName()+ client.getPass());
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(parent);
        }
        catch(IOException io)
        {

        }
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
    void showComplaint(ActionEvent event)
    {
        try {
            main.showAddComplaint(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showDash(ActionEvent event) {
        try {
            main.showHomePage(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Complaints/Dash.fxml"));
            Parent root = loader.load();

            // Loading the controller
            DashController controller = loader.getController();
            controller.initialize(client);
            controller.load();
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    void showEditInfo(ActionEvent event)
    {
        try
        {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/UserInfo/sample.fxml"));
            parent = loader.load();

            UserInfo.Controller c=loader.getController();
            c.setClient(client);
            System.out.println(client.getUserName()+ client.getPass());
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(parent);
        }
        catch(IOException io)
        {

        }

    }

    @FXML
    void showHistory(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Complaints/History.fxml"));
            Parent root = loader.load();

            // Loading the controller
            HistoryController controller = loader.getController();
            controller.load(client);
            controller.setMain(main);
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void showSettings(ActionEvent event)
    {
        infoBut.setVisible(true);
        pasChangeBut.setVisible(true);
    }

    private int pnum,snum;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        infoBut.setVisible(false);
        pasChangeBut.setVisible(false);
       // vbox.getChildren().setAll(pieChart);

    }

    private void detTheNum()
    {
        System.out.println("total size:" + client.getComplaints().size());
        for(int i=0;i<client.getComplaints().size();i++)
        {
            if(client.getComplaints().get(i).getCheck().equals("Pending"))
                pnum++;
            else
            {
                snum++;
            }
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void load()
    {
        usename.setText(getClient().getUserName());
        detTheNum();
        ObservableList<PieChart.Data>pieData= FXCollections.observableArrayList(
                new PieChart.Data("Pending",pnum),
                new PieChart.Data("Solved", snum)
        );

        pieChart.setData(pieData);
        System.out.println(pnum+" "+ snum);
        pLabel.setText("Total Pending Cases : "+String.valueOf(pnum));
        sLabel.setText("Total Solved Cases : "+String.valueOf(snum));
    }
}

