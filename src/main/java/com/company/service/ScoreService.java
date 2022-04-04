package com.company.service;

import com.company.entity.Score;

import java.sql.SQLException;
import java.util.List;

public interface ScoreService {
    /**
     * Adds score to databasee
     */
    void addScore(Score score);

    List<String> topScores();

    void startTimer();
    long stopTimer();

    int countScore(long time, int  basicScore);
}
