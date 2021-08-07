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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Admin;
import sample.Client;
import sample.Main;
import sample.WelcomeController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchCompController {
    private Client client;
    public static List<Complaint> compList=new ArrayList<>();
    public List<Complaint> clientComplaints=new ArrayList<>();
    public List<Complaint> pendComplaints=new ArrayList<>();
    public List<Complaint> solComplaints=new ArrayList<>();
            
    @FXML
    private TableColumn<Complaint,String> userID;

    @FXML
    private TextField userid;

    @FXML
    private Text Pnum;

    @FXML
    private TableColumn<Complaint,String> complainType;

    @FXML
    private Button Solved;

    @FXML
    private Text Snum;

    @FXML
    private Label label;

    @FXML
    private TableColumn<Complaint,String> complaintID;

    @FXML
    private TableColumn<Complaint,String> district;

    @FXML
    private TableView<Complaint> tableview;

    @FXML
    private Button detail;

    @FXML
    private TextField comid;

    @FXML
    private Button Pending;

    @FXML
    private TableColumn<Complaint,String> status;
    private Admin admin;

    @FXML
    private Button solution;
    private Main main;

    @FXML
    void showPending(ActionEvent event) {
        getClientComplaints();
            data = FXCollections.observableArrayList(pendComplaints);

            tableview.setEditable(true);
            tableview.setItems(data);
            tableview.setVisible(true);
            detail.setVisible(true);
            solution.setVisible(true);
    }

    @FXML
    void showSolved(ActionEvent event) {
            getClientComplaints();
            data = FXCollections.observableArrayList(solComplaints);

            tableview.setEditable(true);
            tableview.setItems(data);
            tableview.setVisible(true);
            detail.setVisible(true);
            solution.setVisible(true);
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

                //stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
               /* Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Complaint Details ");
                alert.setContentText(p.toString());
                alert.showAndWait();*/
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

    public void load() {
       // tableview.setVisible(false);
        detail.setVisible(true);
        if (init) {
            initializeColumns();
            init = false;
        }
        solution.setVisible(true);

        //data = FXCollections.observableArrayList(pendComplaint);
        data = FXCollections.observableArrayList(admin.getComplaints());

        tableview.setEditable(true);
        tableview.setItems(data);
    }

    public void initialize(Admin admin) {
        this.admin=admin;
        //compList= Main.getComplaintList();
        compList=DashController.compList;
        //System.out.println("got the list"+ compList.size());
    }

    int pcount = 0,scount = 0;
    public List<Complaint> getClientComplaints(){
        pendComplaints.clear();
        solComplaints.clear();
        clientComplaints.clear();
        for(Complaint c: admin.getComplaints()){
            if(c.getUserId().equals(userid.getText()) || c.getId().equals(comid.getText())){
                clientComplaints.add(c);
                if (c.getCheck().equals("Pending")) {
                    pendComplaints.add(c);
                } else {
                    solComplaints.add(c);
                }
            }
        }
        Pnum.setText(String.valueOf(pendComplaints.size()));
        Snum.setText(String.valueOf(solComplaints.size()));
        Pnum.setVisible(true);
        Snum.setVisible(true);
        return clientComplaints;
    }

    public void showinfo(ActionEvent event) {
        data = FXCollections.observableArrayList(getClientComplaints());

        tableview.setEditable(true);
        tableview.setItems(data);
        getClientComplaints().clear();
    }

    public void backtable(ActionEvent event) {
        solution.setVisible(true);
        pendComplaints.clear();
        solComplaints.clear();
        userid.clear();
        comid.clear();
        Pnum.setVisible(false);
        Snum.setVisible(false);
        //clientComplaints.clear();
        data = FXCollections.observableArrayList(compList);
        //Pnum.setText(String.valueOf(pendComplaints.size()));
        //Snum.setText(String.valueOf(solComplaints.size()));
        tableview.setEditable(true);
        tableview.setItems(data);
    }

    public void SolvedPending(ActionEvent event) throws Exception {
        Complaint p= tableview.getSelectionModel().getSelectedItem();
        if(p.getCheck().equals("Pending")){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Solve a Case");
            alert.setContentText("Do you want to solve this case?");
            Optional<ButtonType> confirm=alert.showAndWait();
            if(confirm.get()==ButtonType.OK)
            {
                System.out.println("ok");
                p.setCheck("Solved");
                main.getNetworkUtil().write(p);
                ComplaintFileOperations.writeToFile(compList);
            }
           // alert.showAndWait();
            //
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Complaint Details");
            alert.setContentText("The case is SOLVED!!");
            alert.showAndWait();
        }
    }

    public void setMain(Main main) 
    {
        this.main=main;
    }
}
