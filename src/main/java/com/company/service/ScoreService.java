package com.company.service;

import java.sql.SQLException;
import java.util.List;

public interface ScoreService {
    /**
     * Adds score to databasee
     */
    void addScore(String player, long score, int level)throws SQLException;

    List<String> topScores() throws SQLException;
    int countScore(long time, int  basicScore);
}
