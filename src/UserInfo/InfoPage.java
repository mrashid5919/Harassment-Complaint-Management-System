package UserInfo;

import Complaints.Complaint;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoPage {

    @FXML
    private Label idLabel;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label catLabel;

    @FXML
    private Label polLabel;

    @FXML
    private Label disLabel;

    @FXML
    private Label statLabel;

    @FXML
    private Label desLabel;
    private  Complaint c;

    public void load()
    {
      idLabel.setText(c.getId());
      userIdLabel.setText(c.getUserId());
      polLabel.setText(c.getPoliceStation());
      disLabel.setText(c.getDistrict());
      statLabel.setText(c.getCheck());
      catLabel.setText(c.getCategory());
      desLabel.setText(c.getDescription());
    }

    public void setC(Complaint c) {
        this.c = c;
    }
}
