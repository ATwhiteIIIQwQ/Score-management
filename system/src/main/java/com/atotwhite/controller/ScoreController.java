package com.atotwhite.controller;

import com.atotwhite.domain.Score;
import com.atotwhite.service.impl.ScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/score")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    private ScoreServiceImpl scoreService;

    @GetMapping("/list")
    public List<Score> list() {
        List<Score> list = scoreService.showAllScore();
        return list;
    }

    @PostMapping
    public String add(@RequestBody Score score) {
        return scoreService.addScore(score);
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable int id, @RequestBody Score score) {
        return scoreService.updateScore(id, score);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id) {
        return scoreService.deleteScore(id);
    }
}
