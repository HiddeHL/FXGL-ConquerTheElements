package com.hsleiden.conquertheelements.Leaderboard;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataController {
    public ArrayList<PlayerRecords> readFile() throws IOException {
        ArrayList<PlayerRecords> records = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\hidde\\OneDrive - Hogeschool Leiden\\HBO\\Jaar 1\\SE-Challengeweek\\GIT\\ConquerTheElements\\ConquerTheElements\\src\\main\\java\\com\\hsleiden\\conquertheelements\\Leaderboard\\LeaderboardDatabase");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] strParts = data.split(";");
                records.add(new PlayerRecords(strParts[0], Integer.parseInt(strParts[1]), Integer.parseInt(strParts[2])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return records;
    }

    public void writeDataToFile(String naam, int level, long time) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\hidde\\OneDrive - Hogeschool Leiden\\HBO\\Jaar 1\\SE-Challengeweek\\GIT\\ConquerTheElements\\ConquerTheElements\\src\\main\\java\\com\\hsleiden\\conquertheelements\\Leaderboard\\LeaderboardDatabase" ,true));
        bw.write(naam + ";" + level + ";" + time + "\n");
        bw.close();
    }
}
