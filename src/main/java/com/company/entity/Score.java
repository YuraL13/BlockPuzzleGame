package com.company.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Score implements Serializable {

    @Id
    private int playerID;

    private String player;
    private int level;
    private long score;

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Score(){

    }

    public Score(String player, int level, int score){
        this.player = player;
        this.level = level;
        this.score = score;
    }

    @Override
    public String toString(){
        return "player=" + player +
                "level=" + level +
                "score=" + score;
    }

}
