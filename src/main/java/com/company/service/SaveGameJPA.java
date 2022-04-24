package com.company.service;

import com.company.entity.Save;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class SaveGameJPA implements SaveGame{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGame(String player, int level) {
            entityManager.persist(new Save(player, level));
    }

    @Override
    public int loadLevel(String player) {
        var a = entityManager.createQuery("SELECT level from SaveGame where " +
                "player='" + player + "' order by level desc;").setMaxResults(1);
        return 0;
    }
}
