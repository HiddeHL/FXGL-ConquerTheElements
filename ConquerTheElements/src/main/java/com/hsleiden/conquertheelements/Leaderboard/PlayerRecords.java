package com.hsleiden.conquertheelements.Leaderboard;

public class PlayerRecords {
    private String username;
    private int enemiesKilled;
    private long timeInSeconds;

    public PlayerRecords(String username, int enemiesKilled, int timeInSeconds) {
        this.username = username;
        this.enemiesKilled = enemiesKilled;
        this.timeInSeconds = timeInSeconds;
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return enemiesKilled;
    }

    public long getTimeInSeconds() {
        return timeInSeconds;
    }
}
