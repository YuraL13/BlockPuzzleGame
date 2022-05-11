package com.company.service.commentservices;

import com.company.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addScore(Rating rating) {
        entityManager.persist(rating);
    }

    @Override
    public List<Rating> getComments() {
        return entityManager.createQuery("select r from Rating r where r.comment is not null or r.comment not like ' '").setMaxResults(15).getResultList();
    }

    @Override
    public String getAvgRating() {
        var avg = entityManager.createQuery("select avg(rating) from Rating").getResultList().get(0).toString();
        return String.valueOf(Math.round(Float.parseFloat(avg)));
    }
}
