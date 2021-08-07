package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminFile {
    private static final String INPUT_FILE_NAME = "Admins.txt";
    private static final String OUTPUT_FILE_NAME = "Admins.txt";

    public static List<Admin> readFromFile() throws Exception {
        List<Admin> Adminlist = new ArrayList();
        // var studentList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        // var br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] tokens = line.split(",");
           Admin client = new Admin();
            client.setName(tokens[0]);
            client.setPassword(tokens[1]);
            Adminlist.add(client);
        }
        br.close();
        return Adminlist;
    }

    public static void writefile(List<Admin> adminList) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Admin a:adminList) {
            bw.write(a.getName() + "," + a.getPassword());
            bw.write("\n");
        }
        bw.close();
    }

    /*public static void writeToFile(List<Client> Clientlist) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        // var bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Client client : Clientlist) {
            bw.write(client.getFullname() + "," + client.getUserName() + ","  + client.getEmail() + "," + client.getGender() + "," + client.getPass());
            bw.write("\n");
        }
        bw.close();
    }*/

}

