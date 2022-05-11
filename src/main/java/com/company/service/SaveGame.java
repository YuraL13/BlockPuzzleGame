package com.company.service;

public interface SaveGame {

    void saveGame(String player, int level);

    int loadLevel(String player);

}
