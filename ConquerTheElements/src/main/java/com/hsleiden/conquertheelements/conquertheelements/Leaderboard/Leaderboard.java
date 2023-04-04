package com.hsleiden.conquertheelements.conquertheelements.Leaderboard;

import java.io.IOException;
import java.util.ArrayList;

public class Leaderboard {

    public ArrayList<PlayerRecords> sortLeaderboard() throws IOException {
        ReadDataFromFile readDataFromFile = new ReadDataFromFile();
        ArrayList<PlayerRecords> records = readDataFromFile.readFile();
        ArrayList<PlayerRecords> sortedRecords = new ArrayList<>();

        while (records.size() > 0) {
            int hoogsteLevel = 0;
            int laagsteTijd = records.get(0).getTimeInSeconds();

            for (PlayerRecords value : records) {
                if (value.getLevel() >= hoogsteLevel) {
                    hoogsteLevel = value.getLevel();
                }
            }

            for (PlayerRecords value2 : records) {
                if (value2.getTimeInSeconds() <= laagsteTijd && value2.getLevel() == hoogsteLevel) {
                    laagsteTijd = value2.getTimeInSeconds();
                }
            }

            int cnt = -1;
            int removeTest = 0;
            for (PlayerRecords value : records) {
                cnt++;
                if (value.getLevel() == hoogsteLevel && value.getTimeInSeconds() == laagsteTijd) {
                    sortedRecords.add(records.get(cnt));
                    removeTest = cnt;
                }
            }
            records.remove(removeTest);
        }
        return sortedRecords;
    }
}
