package Others;


import Complaints.Complaint;
import Complaints.ComplaintFileOperations;
import sample.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
;


public class Server {

    private ServerSocket serverSocket;

    public static List<Client> ClientList=new ArrayList<>();
    public static List<Admin> AdminList=new ArrayList<>();
    public static List<Complaint> complaintsList=new ArrayList<>();
    private HashMap<String, ClientInfo> clientList= new HashMap<String, ClientInfo>();
    Server() {
        //clubs=makeTheMap();
        //System.out.println(clubs.size()+" "+clubs.get("liver234"));
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("server.Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        try {
            ClientList = FileWorks.readFromFile();
            AdminList = AdminFile.readFromFile();
            complaintsList=ComplaintFileOperations.readFromFile();
            setTheList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
       // new ReadThreadServer( clubs,networkUtil);
        new ReadThreadServer( clientList,networkUtil);
    }

    public static void main(String args[])
    {
        System.out.println("main server");
        Server server = new Server();
    }

    public static void setTheList()
    {
        for(int i=0;i< complaintsList.size();i++)
        {
            for(int j = 0; j< ClientList.size(); j++)
            {
                if(complaintsList.get(i).getUserId().equals(ClientList.get(j).getUserName()))
                {
                    ClientList.get(j).addComplaint((complaintsList.get(i)));
                }
            }
        }
        for(int i=0;i< AdminList.size();i++)
        {
            AdminList.get(i).setComplaints(complaintsList);
        }
        System.out.println(AdminList.get(2).getComplaints().size());
    }
}
