package Complaints;

import UserInfo.InfoPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Client;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryController {
    @FXML
    private Button detail;

    @FXML
    private Label Pnum;

    @FXML
    private Label Snum;



    @FXML
    private TableView<Complaint> tableview;

    @FXML
    private TableColumn<Complaint, String> complainType;

    @FXML
    private TableColumn<Complaint, String> complaintID;

    @FXML
    private TableColumn<Complaint, String> district;

    @FXML
    private TableColumn<Complaint, String > status;

    private Main main;

    private Client client;
    public static List<Complaint> compList=new ArrayList<>();
    public static List<Complaint> pendinList = new ArrayList<>();
    public static List<Complaint> solvedList = new ArrayList<>();

    @FXML
    void showDetail(ActionEvent event) {
        Complaint p= tableview.getSelectionModel().getSelectedItem();
        if(p==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error!");
            alert.setContentText("You have to select a case first");
            alert.showAndWait();
        }
        else{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/UserInfo/InfoPage.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            InfoPage controller = loader.getController();
            controller.setC(p);
            controller.load();
            // controller.setMain(this);
            Scene scene = new Scene(root);
            //scene.setFill(Color.TRANSPARENT);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.setTitle("Complaint Info");

            stage.show();

        }
    }

    ObservableList<Complaint> data;

    private boolean init = true;

    private void initializeColumns() {
        complainType.setCellValueFactory(new PropertyValueFactory<>("Category"));

        complaintID.setCellValueFactory(new PropertyValueFactory<>("id"));

        district.setCellValueFactory(new PropertyValueFactory<>("District"));

        status.setCellValueFactory(new PropertyValueFactory<>("check"));

    }

    public void load(Client client) {

        pendinList.clear();
        solvedList.clear();
        this.client=client;
       // List<Complaint> compList=Main.getComplaintList();
       /* List<Complaint> clientComplaints=new ArrayList<>();
        for(Complaint c: compList){
            if(c.getUserId().equals(client.getUserName())){
                clientComplaints.add(c);
            }
        }*/
        if (init) {
            initializeColumns();
            init = false;
        }

        for(Complaint c: client.getComplaints()){
            if(c.getCheck().equals("Pending")){
                pendinList.add(c);
                // System.out.println(pendComplaint.size());
            }
            else{
                solvedList.add(c);
            }
        }
        Pnum.setText(String.valueOf(pendinList.size()));
        Snum.setText(String.valueOf(solvedList.size()));

        data = FXCollections.observableArrayList(client.getComplaints());

        tableview.setEditable(true);
        tableview.setItems(data);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void showpending(ActionEvent event) {
        data = FXCollections.observableArrayList(pendinList);

        tableview.setEditable(true);
        tableview.setItems(data);
        tableview.setVisible(true);
        detail.setVisible(true);
    }

    public void showsolved(ActionEvent event) {
        data = FXCollections.observableArrayList(solvedList);

        tableview.setEditable(true);
        tableview.setItems(data);
        tableview.setVisible(true);
        detail.setVisible(true);
    }
    @FXML
    void back(ActionEvent event) {
        data = FXCollections.observableArrayList(client.getComplaints());

        tableview.setEditable(true);
        tableview.setItems(data);
        tableview.setVisible(true);
        detail.setVisible(true);
    }
}
