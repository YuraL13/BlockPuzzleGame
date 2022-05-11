package com.company.server.controller;

import com.company.entity.Rating;
import com.company.entity.Score;
import com.company.entity.User;
import com.company.service.CommentService;
import com.company.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {

    private User loggedUser;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("scores", scoreService.topScores());
        model.addAttribute("scoreService", scoreService);
        return "index";
    }

    @RequestMapping("/login")
    public String login(String login, String password){
        if("password".equals(password)){
            loggedUser = new User(login);
            return "redirect:/";
        }
        return "redirect:/";
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

    public User getLoggedUser() {
        return loggedUser;
    }



    public boolean isLogged() {
        return loggedUser != null;
    }


}
