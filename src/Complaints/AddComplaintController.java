package Complaints;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Client;
import sample.Main;

import java.util.List;

public class AddComplaintController {
    private Main main;
    private Client client;

    public void init(Client client){
        this.client=client;
    }

    @FXML
    private TextField Type;

    @FXML
    private TextField PoliceStation;

    @FXML
    private TextField District;

    @FXML
    private TextField Detail;

    @FXML
    private Button Submit;

    @FXML
    private Button Home;

    @FXML
    void back(ActionEvent event) {
        try{
            main.showHomePage(client);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void confirm(ActionEvent event) throws Exception{
        String type=Type.getText();
        String ps=PoliceStation.getText();
        String district=District.getText();
        String detail=Detail.getText();
        if(Type.getText().isEmpty() || PoliceStation.getText().isEmpty() || District.getText().isEmpty() || Detail.getText().isEmpty())
        {
            //error
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Required");
            //alert.setHeaderText("Wrong Password Input");
            alert.setContentText("Please Fill All the information");
            alert.showAndWait();
        }
        else
        {
            List<Complaint> compList=Main.getComplaintList();
            int count=0;
            for(Complaint c: compList){
                if(c.getUserId().equals(client.getUserName())){
                    count++;
                }
            }
            Complaint comp=new Complaint();
            comp.setUserId(client.getUserName());
            comp.setCategory(type);
            comp.setPoliceStation(ps);
            comp.setDistrict(district);
            comp.setDescription(detail);
            comp.setId(client.getUserName()+"_"+(count+1));
            comp.setCheck("Pending");
            compList.add(comp);
            Main.setComplaintList(compList);
            ComplaintFileOperations.writeToFile(compList);
            System.out.println("Submitted successfully");
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.setContentText("Your complaint has been submitted successfully");

            main.getNetworkUtil().write(comp);

            alert.showAndWait();
            Type.clear();
            PoliceStation.clear();
            District.clear();
            Detail.clear();
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
