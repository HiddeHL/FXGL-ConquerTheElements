package com.hsleiden.conquertheelements.conquertheelements.Leaderboard;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadDataFromFile {
    public ArrayList<PlayerRecords> readFile() throws IOException {
        ArrayList<PlayerRecords> records = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geert\\OneDrive\\Documenten\\GitHub\\FXGL-ConquerTheElements\\ConquerTheElements\\src\\main\\java\\com\\hsleiden\\conquertheelements\\conquertheelements\\Leaderboard\\LeaderboardDatabase");
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
}
