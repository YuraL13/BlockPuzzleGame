package com.company.service;

import com.company.entity.Rating;
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
        List<Score> a = entityManager.createQuery("SELECT s FROM Score s order by s.score desc").setMaxResults(10).getResultList();
        return a;
    }

    @Override
    public String getRating(){
        var rating = entityManager.createQuery("select avg(rating) from Rating").setMaxResults(1).getResultList();
        return String.valueOf(rating.get(0));
    }

    @Override
    public void setRating(String user, int rating){
        entityManager.persist(new Rating(user, rating));
    }

    @Override
    public void setRating(String user, int rating, String comment) {
        entityManager.persist(new Rating(user, rating, comment));
    }

    @Override
    public List<Rating> getComments(){
        var coments = entityManager.createQuery("select r from Rating r where r.comment is not null").setMaxResults(15).getResultList();
        return coments;
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

        if(time < 20){
            return Math.round(basicScore *4);
        }
        else if(time < 30){
            return (int)Math.round(basicScore*3.75);
        }
        else if(time < 45){
            return (int)Math.round(basicScore*2.65);
        }
        else if(time < 60){
            return (int)Math.round(basicScore *2);
        }
        else if(time < 120){
            return (int)Math.round(basicScore *1.5);
        }
        else return basicScore;

    }
}
