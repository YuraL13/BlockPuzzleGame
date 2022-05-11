package com.company.service;

import com.company.entity.Rating;

import java.util.List;

public interface CommentService {
    void addScore(Rating rating);
    List<Rating> getComments();
    String getAvgRating();
}
