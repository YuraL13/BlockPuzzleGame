package com.company.entity;

public class User{

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private String login;

    public User(String login){
        this.login = login;
    }


}
