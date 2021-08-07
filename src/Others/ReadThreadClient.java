package Others;

import Complaints.Complaint;
import Complaints.DashController;
import Complaints.HistoryController;
import Complaints.SearchCompController;
import javafx.application.Platform;
import sample.*;

import java.io.IOException;



public class ReadThreadClient implements Runnable {
    private Thread thr;
    private Main main;
    private Client cl;
    private Admin ad;


    public ReadThreadClient(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                System.out.println("reading from client");
                if (o != null) {

                    if(o instanceof Client)
                    {
                        cl=(Client)o;
                        System.out.println("got cl in read thread client");
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    main.showHomePage((Client)o);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    if(o instanceof Admin){
                        ad=(Admin)o;
                        System.out.println("got ad in read thread client");
                        DashController.compList.addAll(ad.getComplaints());
                        SearchCompController.compList.addAll(ad.getComplaints());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    main.ShowAdminHomePage((Admin)o);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    if( o instanceof  String)
                    {
                        String type=(String) o;
                        System.out.println(type);
                        if(type.equals("Admin"))
                        {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        main.ShowAdminLogin();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        else if(type.equals("Client"))
                        {
                            main.showLoginPage();
                        }
                    }
                   /* if(o instanceof  ComplaintList)
                    {
                        //for admin
                        ComplaintList c=(ComplaintList) o;
                       // ad.setComplaints(c.getComplaints());
                        for(int i=0;i<c.getComplaints().size();i++)
                        {
                            DashController.compList.add(c.getComplaints().get(i));
                            SearchCompController.compList.add(c.getComplaints().get(i));
                        }
                    }*/
                    if(o instanceof Complaint)
                    {
                       Complaint cp=(Complaint)o;
                       if(cp.getCheck().equalsIgnoreCase("Pending"))
                        {
                           if(cl!=null)
                           {
                               /*HistoryController.compList.add((Complaint) o);
                               Complaint c=(Complaint)o;
                               cl.addComplaint(c);*/
                               Platform.runLater(new Runnable() {
                                   @Override
                                   public void run() {
                                       HistoryController.compList.add((Complaint) o);
                                       Complaint c=(Complaint)o;
                                       cl.addComplaint(c);
                                       /*try {
                                           main.showHomePage(cl);
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                       }*/
                                   }
                               });
                           }
                           if(ad!=null)
                           {
                               DashController.compList.add((Complaint)o);
                               Complaint c=(Complaint)o;
                               ad.addComplaint(c);
                               Platform.runLater(new Runnable() {
                                   @Override
                                   public void run() {
                                       try {
                                           main.ShowAdminHomePage(ad);
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                       }
                                   }
                               });
                           }
                       }
                       else
                       {
                           Complaint c=(Complaint)o;
                           if(cl!=null)
                           {

                               for(int i=0;i< HistoryController.compList.size();i++)
                               {
                                   if(c.getId().equals(HistoryController.compList.get(i).getId()))
                                       HistoryController.compList.remove(i);
                               }
                               HistoryController.compList.add(c);
                               for(int i=0;i< cl.getComplaints().size();i++)
                               {
                                   if(c.getId().equals(cl.getComplaints().get(i).getId()))
                                       cl.getComplaints().remove(i);
                               }
                               cl.addComplaint(c);
                               System.out.println("solved one got message from admin");
                               Platform.runLater(new Runnable() {
                                   @Override
                                   public void run() {
                                       try {
                                           main.showHomePage(cl);
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                       }
                                   }
                               });
                           }
                            if(ad!=null)
                           {
                               for(int i=0;i< DashController.compList.size();i++)
                               {
                                   if(c.getId().equals(DashController.compList.get(i).getId()))
                                       DashController.compList.remove(i);
                               }
                               DashController.compList.add((Complaint)o);
                               for(int i=0;i< ad.getComplaints().size();i++)
                               {
                                   if(c.getId().equals(ad.getComplaints().get(i).getId()))
                                       ad.getComplaints().remove(i);
                               }
                                ad.addComplaint(c);
                               Platform.runLater(new Runnable() {
                                   @Override
                                   public void run() {
                                       try {
                                           main.ShowAdminHomePage(ad);
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                       }
                                   }
                               });
                           }
                       }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error" + e);

        }
    }

}



