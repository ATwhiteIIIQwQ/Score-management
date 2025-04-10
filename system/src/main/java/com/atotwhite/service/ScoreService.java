package com.atotwhite.service;

import com.atotwhite.domain.Score;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScoreService {
    List<Score> showAllScore();
    Page<Score> getScoreByPage(int page, int size);
    String addScore(Score score);
    String updateScore(Integer scoreId, Score score);
    String deleteScore(Integer scoreId);
    Page<Score> searchScore(int page, int size, Integer studentId, Integer courseId, Integer score);
}
