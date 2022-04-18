package com.company.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Score implements Serializable {

    @Id
    @GeneratedValue
    private int playerID;

    private String player;
    private int score;
    private Date date;
    private int level;


    public int getLevel() {
        return level;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = (int)score;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Score(){

    }

    public Score(String player, int level, int score, Date date){
        this.player = player;
        this.level = level;
        this.score = score;
        this.date = date;
    }

    @Override
    public String toString(){
        return "player = " + player +
                " level = " + level +
                " score = " + score;
    }

}
