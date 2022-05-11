package com.company.service.userservices;

import com.company.entity.Gamer;

public interface UserService {

    void registerUser(Gamer newUser);

    /**
     * Check if user is in system and if
     * password is correct
     * @param user User with login and password
     * @return 1 - is user found and password is correct. 2 - if password is wrong or user not fount
     */
    int loginCheck(Gamer user);

    public int ifUserExist(String login, String email);

    /**
     *
     *
     * @param login - user's login
     * @param password - user's password
     * @return 1 - is user found and password is correct. 2 - if password is wrong or user not fount
     */
    int loginCheck(String login, String password);

}
