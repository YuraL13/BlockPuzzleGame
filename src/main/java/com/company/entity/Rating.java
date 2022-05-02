package com.company.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Rating implements Serializable {

    @Id
    @GeneratedValue
    private int ratingID;
    private String userLogin;

    public String getUserLogin() {
        return userLogin;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    private String comment;
    private int rating;

    public Rating(){

    }

    public Rating(String user, int rating){
        this.userLogin = user;
        this.rating = rating;
    }

    public Rating(String user, int rating, String comment){
        this.userLogin = user;
        this.rating = rating;
        this.comment = comment;
    }

}
