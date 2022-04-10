package com.company.service;

import com.company.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) {
        entityManager.persist(score);
    }

    @Override
    public List<Score> topScores() {
        List<Score> a = entityManager.createQuery("SELECT s FROM Score s order by s.score").setMaxResults(10).getResultList();
        return a;
    }

    private long Time;

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
