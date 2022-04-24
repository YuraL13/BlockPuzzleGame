package com.company.server.controller;

import com.company.consoleui.ConsoleUI;
import com.company.core.Field;
import com.company.core.Level;
import com.company.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {
    private final ConsoleUI consoleUI = new ConsoleUI(1); //Change to level generating. In case rewrite consoleUI
//    private Field field = consoleUI.getLevel().getField();

    private Level level = new Level(1);
    private Field field = level.getField();

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/game")
    public String getState(@RequestParam(required = false) String p,
                           @RequestParam(required = false) String row,
                           @RequestParam(required = false) String col,
            Model model){

        if(p != null) {
//            consoleUI.makeMove(consoleUI.getLevel().getPieces().get(Integer.parseInt(p)),
//                    Integer.parseInt(row), Integer.parseInt(col));
            var piece = level.getPieces().get(Integer.parseInt(p));
            level.makeMove(piece, Integer.parseInt(row), Integer.parseInt(col));
        }

        model.addAttribute("scores", scoreService.topScores());
        model.addAttribute("htmlField", getHtmlField());
        model.addAttribute("pieces", getHtmlPieces());

        return "game";
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("scores", scoreService.topScores());
        return "index";
    }

    @RequestMapping("/score")
    public String getTopScores(Model model){
        model.addAttribute("scores", scoreService.topScores());
        return "score";
    }

    public String getHtmlPieces(){
        StringBuilder sb = new StringBuilder();
        var pieces = level.getPieces();

        for (int i = 0; i < pieces.size(); i++) {
            sb.append("<div class='piece' >");
            sb.append("<div>" + i +"</div>");
            sb.append(printPiece(pieces.get(i).getPiece()));
            sb.append("</div>");

        }
        return sb.toString();
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
        sb.append("<table>\n");
        for(int i = 0; i < arr.length; i++){
            sb.append("<tr>\n");
            for (int j = 0; j < arr.length; j++){
                sb.append("<td>\n");
                sb.append(arr[i][j]);
                sb.append("</td>\n");
            }
//            if(Arrays.stream(arr[i]).toList().contains("X"))
//                sb.append("<br>");
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
                if(field.getField()[row][column] == 0) {
                    sb.append("<img src='/images/X.png'/>");
                }
                else {
                    sb.append("<img src='/images/1.png'/>");
                }
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }

        sb.append("</table>\n");
        return sb.toString();

    }



}
