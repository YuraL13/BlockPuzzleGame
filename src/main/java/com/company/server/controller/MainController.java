package com.company.server.controller;

import com.company.entity.Gamer;
import com.company.entity.Rating;
import com.company.entity.Score;
import com.company.service.commentservices.CommentService;
import com.company.service.scoreservices.ScoreService;
import com.company.service.userservices.CurrentPlayer;
import com.company.service.userservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Date;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {

    private Gamer loggedUser;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CurrentPlayer currentPlayer;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("scores", scoreService.topScores());
        model.addAttribute("scoreService", scoreService);
        return "index";
    }

    @RequestMapping("/login")
    public String login(String login, String password) throws IOException {

        System.out.println(login + " - " + password);
        if(userService.loginCheck(login, password) == 1){
            loggedUser = new Gamer(login);
            currentPlayer.setPlayer(login);
            return "redirect:/";
        }
        return "redirect:/register";
    }

    @RequestMapping("register")
    public String register(String login, String email, String password, Model model){
        var user = new Gamer(login, email, password);
        String err = null;

        if(userService.ifUserExist(login, email) == 1){
            err = "This user already exists";
            model.addAttribute("regErr", err);
            return "register";
        }


        userService.registerUser(new Gamer(login, email, password));
        if(userService.loginCheck(login, password) == 1){
            return "redirect:/";
        }

        model.addAttribute("regErr", err);
        return "register";
    }

    @RequestMapping("/logout")
    public String logout(){
        loggedUser = null;
        return "redirect:/";
    }

    @RequestMapping("/rating/post")
    public String rating(String star, String comment){
        System.out.println(star + "   "  + comment);
        var r = Integer.parseInt(star);

        if(r >= 1 && r <= 5){
            if(comment != null){
                commentService.addScore(new Rating(loggedUser.getLogin(), r, comment));
            }
            commentService.addScore(new Rating(loggedUser.getLogin(), r));
            return "redirect:/rating";
        }
        return "rating";
    }

    @RequestMapping("/rating")
    public String rating(Model model){
        model.addAttribute("rating", scoreService.getRating());
        model.addAttribute("comments", commentService.getComments());
        model.addAttribute("avgRating", commentService.getAvgRating());
        return "rating";
    }

    @RequestMapping("/game/addScore")
    public String addScore(@RequestParam(required = true) String size,
                           @RequestParam(required = true) String level){
        scoreService.addScore(new Score(getLoggedUser().getLogin(),
                Integer.parseInt(level), Integer.parseInt(size), new Date()));
        return "redirect:/game&level";
    }

    public Gamer getLoggedUser() {
        return loggedUser;
    }



    public boolean isLogged() {
        return loggedUser != null;
    }


}
