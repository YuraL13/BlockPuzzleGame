package com.company.service.userservices;

import com.company.entity.Gamer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class UserServiceJPA implements UserService {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void registerUser(Gamer newUser) {
        entityManager.persist(newUser);
    }

    @Override
    public int ifUserExist(String login, String email){
        var res = entityManager.createQuery("select u.login from Gamer u" +
                " where u.login = '" + login +"'" +
                " or u.email='" + email +"'").getResultList();
        if(res.size() == 0) return 0;
        return 1;
    }

    @Override
    public int loginCheck(Gamer user) {
        var login = user.getLogin();
        var result = entityManager.createQuery("select u from Gamer u " +
                "where u.password = '" + user.getPassword() +"' " +
                "and u.login ='"+ user.getLogin() +"'").getResultList();

        if(result.size() == 0){return 0;}
        return 1;
    }

    @Override
    public int loginCheck(String login, String password) {
        System.out.println(login + "-test LOGIN");
        var result = entityManager.createQuery("select u.login from Gamer u " +
                "where u.password = '" + password +"' " +
                "and u.login ='"+ login +"'").getResultList();

        System.out.println(result.size());

        if(result.size() == 0){return 0;}
        return 1;
    }
}
