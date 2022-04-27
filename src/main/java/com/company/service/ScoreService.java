package com.company.service;

import com.company.entity.Score;

import java.util.List;

public interface ScoreService {
    /**
     * Adds score to databasee
     */
    void addScore(Score score);

    List<Score> topScores();
    String getRating();
    void setRating(String user, int rating);

    void startTimer();
    long stopTimer();

    int countScore(long time, int  basicScore);
}
