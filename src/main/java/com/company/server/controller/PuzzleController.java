package com.company.server.controller;

import com.company.consoleui.ConsoleUI;
import com.company.core.Field;
import com.company.core.GameState;
import com.company.core.Level;
import com.company.entity.Score;
import com.company.service.scoreservices.ScoreService;
import com.company.service.userservices.CurrentPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Date;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/game")
public class PuzzleController {
    private final ConsoleUI consoleUI = new ConsoleUI(1); //Change to level generating. In case rewrite consoleUI
//    private Field field = consoleUI.getLevel().getField();
    private int levelNumber = 1;
    private Level level = new Level(levelNumber);
    private Field field = level.getField();

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CurrentPlayer currentPlayer;

    @RequestMapping
    public String getState(@RequestParam(required = false) String levelNumber,
                           @RequestParam(required = false) String p,
                           @RequestParam(required = false) String row,
                           @RequestParam(required = false) String col,
            Model model) throws FileNotFoundException {

        processCommand(levelNumber, p, row, col);
        prepareModel(model);
        return "game";
    }

    @RequestMapping("/score")
    public String getTopScores(Model model){
        model.addAttribute("scores", scoreService.topScores());
        return "score";
    }

    public String getLevelNumber(){
        return String.valueOf(levelNumber);
    }

    @RequestMapping("/new")
    public String newGame(
            Model model){
            level = new Level(levelNumber);
            field = level.getField();
        return "redirect:/game";
    }

    public String getHtmlPieces(){
        StringBuilder sb = new StringBuilder();
        var pieces = level.getPieces();


        String first = "style='border: 2px solid white'";
        for (int i = 0; i < pieces.size(); i++) {
            if(i!=0){
                first = "";
            }
            sb.append("<div "+ first +"  class='piece' >");
            sb.append("<div style='font-size: 30px;' >" + i +"</div>");
            sb.append(printPiece(pieces.get(i).getPiece()));
            sb.append("</div>");

        }
        return sb.toString();
    }

    @RequestMapping(value = "/field", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String game(@RequestParam(required = false) String p,
                       @RequestParam(required = false) String row,
                       @RequestParam(required = false) String col){
        if(p != null && row != null && col != null) {
            var piece = level.getPieces().get(Integer.parseInt(p));
            level.makeMove(piece, Integer.parseInt(row), Integer.parseInt(col));
            field.isGameFinished();
            field = level.getField();
        }

        return getHtmlField();
    }

    private String printPiece(Point[] p){ //Func to print a piece
        StringBuilder sb = new StringBuilder();
        //Creates 2D array with blanc spaces and then replaces right coordinates with X
        String[][] arr = new String[p.length][p.length];

        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                arr[i][j] = " ";
            }
        }

        for (Point a : p){
            arr[a.x][a.y] = "<img src='images/1.png'/>";
        }

        sb.append("<table class='piece'>\n");
        for(int i = 0; i < arr.length; i++){
            sb.append("<tr>\n");
            for (int j = 0; j < arr.length; j++){
                sb.append("<td>\n");
                sb.append(arr[i][j]);
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    public String getHtmlField(){
        StringBuilder sb = new StringBuilder();

        sb.append("<table>\n");
        for (int row = 0; row < field.getRowCount(); row++) {
            sb.append("<tr>\n");
            for (int column = 0; column < field.getColCount(); column++) {
                //var tile = field.getTile(row, column);
                sb.append("<td>\n");
                sb.append(String.format("<a href='/game?p=%d&row=%d&col=%d'>\n", 0, row, column));
                if(field.getField()[row][column] == 0) {
                    sb.append("<img src='/images/X.png'/>");
                }
                else {
                    sb.append("<img src='/images/1.png'/>");
                }
                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }

        sb.append("</table>\n");
        return sb.toString();

    }

    private void prepareModel(Model model){
        model.addAttribute("htmlField", getHtmlField());
        model.addAttribute("pieces", getHtmlPieces());
        model.addAttribute("scores", scoreService.topScores());
        model.addAttribute("game", level);

    }

    private void processCommand(String levelNumber, String p, String row, String col) throws FileNotFoundException {
        if(levelNumber!=null && p == null){
            var levelN = Integer.parseInt(levelNumber);
            if (levelN > 3){levelN = 1;}
            level = new Level(levelN);
            field = level.getField();
            this.levelNumber = levelN;
        }

        if(p != null && row != null && col != null) {
            var piece = level.getPieces().get(Integer.parseInt(p));
            level.makeMove(piece, Integer.parseInt(row), Integer.parseInt(col));
            field.isGameFinished();
            if(field.getState() == GameState.SOLVED){
                scoreService.addScore(new Score(currentPlayer.getPlayer(), level.getLevelNumber()-1, field.size(), new Date()));
            }
        }
    }

}
