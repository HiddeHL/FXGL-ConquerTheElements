package com.hsleiden.conquertheelements.conquertheelements.Leaderboard;

public class PlayerRecords {
    private String username;
    private int level;
    private int timeInSeconds;

    public PlayerRecords(String username, int level, int timeInSeconds) {
        this.username = username;
        this.level = level;
        this.timeInSeconds = timeInSeconds;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }
}
