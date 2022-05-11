package com.company.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Gamer implements Serializable {

    @Id
    @GeneratedValue
    private int userID;
    private String email;
    private String login;

    public Gamer() {
    }

    public String getPassword() {
        return password;
    }

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Gamer(String login){
        this.login = login;
        this.password = "password";
    }

    public Gamer(String login, String email, String password){
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Gamer(String login, String password){}


}
