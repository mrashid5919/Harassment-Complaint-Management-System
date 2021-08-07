package Complaints;

import sample.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ComplaintFileOperations {
    private static final String INPUT_FILE_NAME = "Complaints.txt";
    private static final String OUTPUT_FILE_NAME = "Complaints.txt";

    public static List<Complaint> readFromFile() throws Exception {
        List<Complaint> complaintList = new ArrayList();
        // var studentList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        // var br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] tokens = line.split(",");
            Complaint complaint=new Complaint();
            complaint.setUserId(tokens[0]);
            complaint.setCategory(tokens[1]);
            complaint.setPoliceStation(tokens[2]);
            complaint.setDistrict(tokens[3]);
            complaint.setDescription(tokens[4]);
            complaint.setId(tokens[5]);
            complaint.setCheck(tokens[6]);
            complaintList.add(complaint);
        }
        br.close();

        return complaintList;
    }
    public static void writeToFile(List<Complaint> complaintList) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        // var bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for(Complaint complaint: complaintList){
            bw.write(complaint.getUserId() + "," + complaint.getCategory() + "," + complaint.getPoliceStation() + "," + complaint.getDistrict() + "," + complaint.getDescription() + "," + complaint.getId() + "," + complaint.getCheck());
            bw.write("\n");
        }
        bw.close();
    }
}
