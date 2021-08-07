package sample;

import Complaints.Complaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable
{
    private String  userName,gender,pass,fullname;
    private String email;
    private List<Complaint> complaints=new ArrayList<>();


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullname(String fullname){this.fullname = fullname;
    }
    public String getFullname() {return this.fullname;}

    public List<Complaint> getComplaints() {
        return complaints;
    }
    public void addComplaint(Complaint c)
    {
        complaints.add(c);
    }
    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
    public void replaceClomplain(Complaint c)
    {
        for(int i=0;i<complaints.size();i++)
        {
            if(c.getUserId().equals(complaints.get(i).getUserId()))
            {
                complaints.remove(i);
            }
        }
    }
}
