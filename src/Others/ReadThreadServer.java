package Others;


import Complaints.Complaint;
import sample.Admin;
import sample.Client;
import sample.FileWorks;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private NetworkUtil networkUtil;
    private Client c;
    
    public static HashMap<String, ClientInfo> clients;


    public ReadThreadServer(HashMap<String, ClientInfo> clients, NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        this.clients = clients;
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {

                    if(o instanceof Client)
                    {
                        Client cl=(Client)o;
                        c=cl;
                        /// file read
                        for(Complaint c: Server.complaintsList){
                            if(c.getUserId().equals(cl.getUserName())){
                                cl.addComplaint(c);
                            }
                        }
                        ClientInfo clientInfo = new ClientInfo();
                        clientInfo.setUserName(cl.getUserName());
                        clientInfo.setOnline(true);
                        clientInfo.setClient(true);
                        clientInfo.setNetworkUtil(networkUtil);
                        clients.put(cl.getUserName(), clientInfo);
                        networkUtil.write(cl);
                        System.out.println("writing client");
                    }
                    if(o instanceof Admin){
                        Admin a=(Admin)o;
                        a.setComplaints(Server.complaintsList);
                        ClientInfo clientInfo=new ClientInfo();
                        clientInfo.setUserName(a.getName());
                        clientInfo.setOnline(true);
                        clientInfo.setNetworkUtil(networkUtil);
                        clientInfo.setClient(false);
                        clients.put(a.getName(), clientInfo);
                        networkUtil.write(a);
                    }
                    if( o instanceof  String)
                    {
                        String type=(String) o;
                        System.out.println(type);
                        if(type.equals("Admin"))
                        {
                            networkUtil.write("Admin");

                            ComplaintList cl=new ComplaintList();
                            cl.setComplaints(Server.complaintsList);
                            networkUtil.write(cl);
                            System.out.println("sending list to admin");
                        }
                        else if(type.equals("Client"))
                        {
                           //List<Complaint> complaints=new ArrayList<>();
                            networkUtil.write("client");
                            System.out.println("client chosen");
                        }
                        /*else if(type.equals("Admin logged in Successfully"))
                        {
                            List<Complaint> complaints = new ArrayList<>(Server.complaintsList);
                            ComplaintList cl=new ComplaintList();
                            cl.setComplaints(complaints);
                            networkUtil.write(cl);
                            System.out.println("send list to admin");
                        }*/
                    }
                    if(o instanceof  Complaint)
                    {
                        Complaint cp=(Complaint)o;
                        if(cp.getCheck().equals("Pending"))
                        {
                            ///while adding change id
                            ///adding to server to update next login time
                            Server.complaintsList.add(cp);

                            cp.setId(cp.getUserId()+"_"+(c.getComplaints().size()+1));
                            c.addComplaint((Complaint) o);
                            //ComplaintList.
                            Iterator<String> iterator = clients.keySet().iterator();
                            while (iterator.hasNext()) {
                                String name = iterator.next();
                                //
                                ClientInfo clientInfo = clients.get(name);
                                //clients.values();
                                if((clientInfo.getUserName().equals(c.getUserName())))
                                    clientInfo.getNetworkUtil().write((Complaint) o);
                                else if(!clientInfo.isClient())
                                {
                                    clientInfo.getNetworkUtil().write((Complaint) o);
                                    //write to update admin
                                }
                            }
                        }
                        else
                        {
                            //solved complaint send the client and admin
                            System.out.println(checkTheClient(cp.getUserId()));
                            if(checkTheClient(cp.getUserId()))
                            {
                                //send that client and admin
                                Iterator<String> iterator = clients.keySet().iterator();
                                while (iterator.hasNext()) {
                                    String name = iterator.next();
                                    ClientInfo clientInfo = clients.get(name);
                                    ///here difference
                                    if((clientInfo.getUserName().equals(cp.getUserId())))
                                        clientInfo.getNetworkUtil().write((Complaint) o);
                                    else if(!clientInfo.isClient())
                                    {
                                        clientInfo.getNetworkUtil().write((Complaint) o);
                                        System.out.println("is sending updating list??");
                                        //write to update admin

                                    }
                                }
                            }
                        }
                    }
            }
        } }catch (Exception e) {
            System.out.println("From server: " + e.getMessage());
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public boolean checkTheClient(String userid)
    {
        List<Client>cl=new ArrayList<>();
        try{
            cl= FileWorks.readFromFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    for(int i=0;i<cl.size();i++)
        {
            if(userid.equals(cl.get(i).getUserName()))
            {
                return true;
            }
        }
        return false;
    }
}



