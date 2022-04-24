package com.company.entity;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Save implements Serializable {

    @Id
    @GeneratedValue
    private int saveID;

    @NotNull
    private String player;
    @NotNull
    private int level;

    public Save() {
    }

    public Save(String player, int level){
        this.player = player;
        this.level = level;
    }
}
