package com.company.server.controller;

import com.company.entity.User;
import com.company.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {

    private User loggedUser;

    @Autowired
    private ScoreService scoreService;

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

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean isLogged() {
        return loggedUser != null;
    }


}
