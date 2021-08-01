import java.io.Serializable;

public class Complaint implements Serializable {
    private String Category;
    private String PoliceStation;
    private String District;
    private String Description;
    private int id;

    public Complaint(){

    }

    public Complaint(String category, String policeStation, String district, String description, int id) {
        Category = category;
        PoliceStation = policeStation;
        District = district;
        Description = description;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
