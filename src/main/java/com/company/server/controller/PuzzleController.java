package com.company.server.controller;

import com.company.consoleui.ConsoleUI;
import com.company.core.Field;
import com.company.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("/game")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {
    private final ConsoleUI consoleUI = new ConsoleUI(1);
    private Field field = consoleUI.getLevel().getField();

    public String state = field.getState().toString();
    public String fieldUI = field.getField();

    @Autowired
    private ScoreService scoreService;

    @RequestMapping
    public String getState(
            Model model){
        model.addAttribute("scores", scoreService.topScores());
        model.addAttribute("htmlField", getHtmlField());
        return "game";
    }

    @RequestMapping("/scores")
    public String getTopScores(Model model){
        model.addAttribute("scores", scoreService.topScores());
        return "score";
    }

    public String getHtmlField(){
        StringBuilder sb = new StringBuilder();

        sb.append("<table>\n");
        for (int row = 0; row < field.getRowCount(); row++) {
            sb.append("<tr>\n");
            for (int column = 0; column < field.getColCount(); column++) {
                //var tile = field.getTile(row, column);
                sb.append("<td>\n");
                sb.append("X");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }

        sb.append("</table>\n");
        return sb.toString();

    }



}
