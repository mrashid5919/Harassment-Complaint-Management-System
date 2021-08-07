package Others;

import Complaints.Complaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComplaintList implements Serializable
{
    private List<Complaint> complaints= new ArrayList<>();


    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
}
