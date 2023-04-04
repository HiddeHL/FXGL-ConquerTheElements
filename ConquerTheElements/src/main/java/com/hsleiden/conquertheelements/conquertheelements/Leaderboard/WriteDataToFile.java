package com.hsleiden.conquertheelements.conquertheelements.Leaderboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDataToFile {

    public void writeDataToFile(String naam, int level, int time) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\geert\\OneDrive\\Documenten\\GitHub\\FXGL-ConquerTheElements\\ConquerTheElements\\src\\main\\java\\com\\hsleiden\\conquertheelements\\conquertheelements\\Leaderboard\\LeaderboardDatabase" ,true));
        bw.write(naam + ";" + level + ";" + time + "\n");
        bw.close();
    }
}
