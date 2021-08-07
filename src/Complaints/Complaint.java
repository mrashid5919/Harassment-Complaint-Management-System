package Complaints;

import java.io.Serializable;

public class Complaint implements Serializable {
    private String userId;

    private String Category;
    private String PoliceStation;
    private String District;
    private String Description;
    private String id;
    private String check;

    public Complaint(){

    }

    public Complaint(String category, String policeStation, String district, String description, String id) {
        this.Category = category;
        this.PoliceStation = policeStation;
        this.District = district;
        this.Description = description;
        this.id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPoliceStation() {
        return PoliceStation;
    }

    public void setPoliceStation(String policeStation) {
        PoliceStation = policeStation;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String toString() {
        return "User:   "+getUserId() + "\nType:   " + getCategory() + "\nPolice Station:  " + getPoliceStation()+"\nDistrict:  "+getDistrict()+"\nDetails: "+getDescription()+"\nStatus:    "+getCheck();
    }
}
