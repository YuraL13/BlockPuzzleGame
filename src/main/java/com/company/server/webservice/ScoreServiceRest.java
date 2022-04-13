package com.company.server.webservice;

import com.company.entity.Score;
import com.company.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreServiceRest {

    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public List<Score> topScores(){
        return scoreService.topScores();
    }

    @PostMapping
    public void addScore(@RequestBody Score score){
        scoreService.addScore(score);
    }

}
