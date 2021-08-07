package Complaints;

import UserInfo.InfoPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Admin;
import sample.Client;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashController {

    private Client client;
    public static List<Complaint> compList=new ArrayList<>();
    public List<Complaint> clientComplaints=new ArrayList<>();
    public List<Complaint> pendComplaint=new ArrayList<>();
    public List<Complaint> solComplaint=new ArrayList<>();

    @FXML
    private Button Pending;

    @FXML
    private Button Solved;

    @FXML
    private Button detail;

    @FXML
    private Text Pnum;

    @FXML
    private Text Snum;

    @FXML
    private TableView<Complaint> tableview;

    @FXML
    private TableColumn<Complaint, String> userID;

    @FXML
    private TableColumn<Complaint, String> complainType;

    @FXML
    private TableColumn<Complaint, String> complaintID;

    @FXML
    private TableColumn<Complaint, String> district;

    @FXML
    private TableColumn<Complaint, String> status;
    private Admin admin;

    @FXML
    void showPending(ActionEvent event) {

        data = FXCollections.observableArrayList(pendComplaint);

        tableview.setEditable(true);
        tableview.setItems(data);
        tableview.setVisible(true);
        detail.setVisible(true);
    }

    @FXML
    void showSolved(ActionEvent event){

        data = FXCollections.observableArrayList(solComplaint);

        tableview.setEditable(true);
        tableview.setItems(data);
        tableview.setVisible(true);
        detail.setVisible(true);
    }

    public void showAll()
    {
        data = FXCollections.observableArrayList(compList);

        tableview.setEditable(true);
        tableview.setItems(data);
        tableview.setVisible(true);
        detail.setVisible(true);
    }

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
            if(client!=null)
            {
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Complaint Details");
                alert.setContentText(p.toString());
                alert.showAndWait();
            }
            else
            {

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
    }

    ObservableList<Complaint> data;

    private boolean init = true;

    private void initializeColumns() {
        userID.setCellValueFactory(new PropertyValueFactory<>("userId"));

        complainType.setCellValueFactory(new PropertyValueFactory<>("Category"));

        complaintID.setCellValueFactory(new PropertyValueFactory<>("id"));

        district.setCellValueFactory(new PropertyValueFactory<>("District"));

        status.setCellValueFactory(new PropertyValueFactory<>("check"));

    }

    public void initialize(Client client) {
        //tableview.setVisible(false);
        this.client = client;
        //compList = Main.getComplaintList();
        /*for (Complaint c : compList) {
            if (c.getUserId().equals(client.getUserName())) {
                clientComplaints.add(c);
            }
        }*/
        for (Complaint c : client.getComplaints()) {
            if (c.getCheck().equals("Pending")) {
                pendComplaint.add(c);
            } else {
                solComplaint.add(c);
            }
        }
        Pnum.setText(String.valueOf(pendComplaint.size()));
        Snum.setText(String.valueOf(solComplaint.size()));
        System.out.println(pendComplaint.size());
        System.out.println(solComplaint.size());
    }
        public void initialize(Admin admin){

        pendComplaint.clear();
        solComplaint.clear();

            //tableview.setVisible(false);
            this.admin=admin;
            //compList=Main.getComplaintList();

            System.out.println("got the list"+ admin.getComplaints().size());
            System.out.println(pendComplaint.size());
            System.out.println(solComplaint.size());

            for(Complaint c: admin.getComplaints()){
                if(c.getCheck().equals("Pending")){
                    pendComplaint.add(c);
                   // System.out.println(pendComplaint.size());
                }
                else{
                    solComplaint.add(c);
                }
            }
        Pnum.setText(String.valueOf(pendComplaint.size()));
        Snum.setText(String.valueOf(solComplaint.size()));
            System.out.println(pendComplaint.size());
            System.out.println(solComplaint.size());
    }

    public void load() {
        tableview.setVisible(false);
        detail.setVisible(false);
        if (init) {
            initializeColumns();
            init = false;
        }

        //data = FXCollections.observableArrayList(pendComplaint);
        data = FXCollections.observableArrayList(compList);

        tableview.setEditable(true);
        tableview.setItems(data);
    }

}
