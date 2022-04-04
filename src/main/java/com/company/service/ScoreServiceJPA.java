package com.company.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

public class ScoreServiceJPA implements ScoreService{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addScore(String player, long score, int level) throws SQLException {
        entityManager.persist(player);
    }

    @Override
    public List<String> topScores() throws SQLException {
        return null;
    }

    @Override
    public int countScore(long time, int basicScore) {
        return 0;
    }
}
