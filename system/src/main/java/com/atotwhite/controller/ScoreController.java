package com.atotwhite.controller;

import com.atotwhite.domain.Score;
import com.atotwhite.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/score")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/list")
    public List<Score> list() {
        return scoreService.showAllScore();
    }

    @GetMapping("/page")
    public Page<Score> page(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return scoreService.getScoreByPage(pageNum, pageSize);
    }

    @PostMapping
    public String add(@RequestBody Score score) {
        return scoreService.addScore(score);
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Score score) {
        return scoreService.updateScore(id, score);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Integer id) {
        return scoreService.deleteScore(id);
    }

    @GetMapping("/search")
    public List<Score> search(@ModelAttribute Score score) {
        return scoreService.searchScore(score);
    }
}

