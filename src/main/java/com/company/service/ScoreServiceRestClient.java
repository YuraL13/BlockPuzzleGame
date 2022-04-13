package com.company.service;

import com.company.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceRestClient implements ScoreService{

    @Value("${remote.server.api}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url + "/score", score, Score.class);
    }

    @Override
    public List<Score> topScores() {
        return Arrays.asList(restTemplate.getForEntity(url + "/score", Score[].class).getBody());
    }

    private long Time;

    @Override
    public void startTimer(){
        Time = System.nanoTime();
    }

    /**
     * Stops timer
     * @return time in seconds
     */
    public long stopTimer(){
        Time = System.nanoTime() - Time;
        return Time/1000000000;
    }
    @Override
    public int countScore(long time, int  basicScore){

        if(time < 10){
            return Math.round(basicScore *2);
        }
        else if(time < 60){
            return (int)Math.round(basicScore *1.5);
        }
        else if(time < 120){
            return (int)Math.round(basicScore *1.25);
        }
        else return basicScore;

    }
}
