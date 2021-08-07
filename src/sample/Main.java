package sample;

import Complaints.AddComplaintController;
import Complaints.Complaint;
import Complaints.ComplaintFileOperations;
import Others.NetworkUtil;
import Others.ReadThreadClient;
import Others.Server;
import UserInfo.AdminHomePage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private Client client;
    private Stage stage = new Stage();
    public static List<sample.Client> ClientList;
    public static List<Admin> AdminList;
    private NetworkUtil nu;

    public static List<Complaint> complaintList=new ArrayList<>();

    public static List<Complaint> getComplaintList() {
        return complaintList;
    }

    public static void setComplaintList(List<Complaint> complaintList) {
        Main.complaintList = complaintList;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        ClientList = FileWorks.readFromFile();
        AdminList = AdminFile.readFromFile();
        complaintList= ComplaintFileOperations.readFromFile();
        connectToServer();
        ShowWelcome();
    }
    public void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        nu = new NetworkUtil(serverAddress, serverPort);
        new ReadThreadClient(this);
    }

    public void ShowWelcome() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Welcome.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        WelcomeController controller = loader.getController();
        controller.setMain(this);
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.setOnCloseRequest(e-> System.exit(1));
    }

    public void showHomePage(sample.Client cl) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomePage.fxml"));
        System.out.println("before");
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));

        HomePage controller = loader.getController();
        controller.setMain(this);
        controller.setClient(cl);
        controller.load();
        System.out.println("after");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public Client getClient() {
        return client;
    }
    public void showLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setMain(this);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    public void ShowAdminLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminLogin.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        AdminLoginController controller = loader.getController();
        controller.setMain(this);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }
    public void ShowAdminHomePage(Admin admin) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UserInfo/AdminHomePage.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        AdminHomePage controller = loader.getController();
        controller.setMain(this);
        controller.setAdmin(admin);
        controller.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }
    public void showAddComplaint(Client client) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Complaints/AddComplaint.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddComplaintController controller = loader.getController();
        controller.init(client);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Add complaint");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void showPasChangePage(Client c)
    {

    }

    public NetworkUtil getNetworkUtil() {
        return nu;
    }


}
