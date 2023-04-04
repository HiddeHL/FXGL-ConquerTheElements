package com.hsleiden.conquertheelements.Leaderboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDataToFile {

    public void writeDataToFile(String naam, int level, int time) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\hidde\\OneDrive - Hogeschool Leiden\\HBO\\Jaar 1\\SE-Challengeweek\\GIT\\ConquerTheElements\\ConquerTheElements\\src\\main\\java\\com\\hsleiden\\conquertheelements\\Leaderboard\\LeaderboardDatabase" ,true));
        bw.write(naam + ";" + level + ";" + time + "\n");
        bw.close();
    }
}
