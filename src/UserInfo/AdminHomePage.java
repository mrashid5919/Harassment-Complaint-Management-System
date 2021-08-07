package UserInfo;

import Complaints.DashController;
import Complaints.SearchCompController;
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
import sample.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomePage implements Initializable {
    private sample.Main main;
    @FXML
    private Button DashBut;

    @FXML
    private Label adminname;

    @FXML
    private Button complaintBut;

    @FXML
    private Button searchBut;

    @FXML
    private Button setBut;

    @FXML
    private Button pasChangeBut;

    @FXML
    private Button logoutBut;
    @FXML
    private PieChart pieChart;

    @FXML
    private Label pLabel;

    @FXML
    private Label sLabel;

    @FXML
    private VBox vbox;
    private Parent parent;

    private Admin admin;
    int pnum,snum;

    public void setAdmin(Admin a){admin = a;}

    @FXML
    void changePass(ActionEvent event)
    {
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/UserInfo/EditPassAdmin.fxml"));
            parent = loader.load();

            EditPassAdmin ep=loader.getController();
            ep.setAdmin(admin);
            System.out.println(admin.getName()+", "+admin.getPassword());
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(parent);
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    @FXML
    void logout(ActionEvent event)
    {
        try {
            main.ShowWelcome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchOp(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Complaints/SearchComplaints.fxml"));
            Parent root = loader.load();

            // Loading the controller
            SearchCompController controller = loader.getController();
            controller.initialize(admin);
            controller.setMain(main);
            controller.showAll();
            controller.load();

            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showComplaint(ActionEvent event)
    {
            ///my part
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Complaints/Dash.fxml"));
            Parent root = loader.load();

            // Loading the controller
            DashController controller = loader.getController();
            controller.initialize(admin);
            controller.load();
            controller.showAll();
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showDash(ActionEvent event)
    {
        try {
            main.ShowAdminHomePage(admin);
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Complaints/Dash.fxml"));
            Parent root = loader.load();

            // Loading the controller
            DashController controller = loader.getController();
            controller.initialize(admin);
            controller.load();
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    void showSettings(ActionEvent event)
    {
        pasChangeBut.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        pasChangeBut.setVisible(false);
    }

    public void setMain(sample.Main main)
    {
        this.main=main;
    }

    public void load()
    {
        System.out.println(admin.getComplaints().size());
       // admin.setComplaints(DashController.compList);
        detTheNum();
        ObservableList<PieChart.Data> pieData= FXCollections.observableArrayList(
                new PieChart.Data("Pending",pnum),
                new PieChart.Data("Solved", snum)
        );

        pieChart.setData(pieData);
        System.out.println(pnum+" "+ snum);
        pLabel.setText("Total Pending Cases : "+String.valueOf(pnum));
        sLabel.setText("Total Solved Cases : "+String.valueOf(snum));
    }
    private void detTheNum() {
        System.out.println("total size:" + admin.getComplaints().size());
        for(int i=0;i<admin.getComplaints().size();i++)
        {
            if(admin.getComplaints().get(i).getCheck().equals("Pending"))
                pnum++;
            else
            {
                snum++;
            }
        }
    }
}
