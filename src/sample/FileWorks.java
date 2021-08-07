package sample;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorks {
    private static final String INPUT_FILE_NAME = "Users.txt";
    private static final String OUTPUT_FILE_NAME = "Users.txt";

    public static List<Client> readFromFile() throws Exception {
        List<Client> Clientlist = new ArrayList();
        // var studentList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        // var br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] tokens = line.split(",");
            Client client = new Client();
            client.setFullname(tokens[0]);
            client.setUserName(tokens[1]);
           //client.setUserId(tokens[2]);
            client.setEmail(tokens[2]);
            client.setGender(tokens[3]);
            client.setPass(tokens[4]);
            Clientlist.add(client);
        }
        br.close();
        return Clientlist;
    }

    public static void writeToFile(List<Client> Clientlist) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        // var bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Client client : Clientlist) {
            bw.write(client.getFullname() + "," + client.getUserName() + ","  + client.getEmail() + "," + client.getGender() + "," + client.getPass());
            bw.write("\n");
        }
        bw.close();
    }

}
