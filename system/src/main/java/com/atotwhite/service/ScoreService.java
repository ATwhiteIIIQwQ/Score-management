package com.atotwhite.service;

import com.atotwhite.domain.Score;

import java.util.List;

public interface ScoreService {
    List<Score> showAllScores();
    String addScore(Score score);
    String updateScore(Integer scoreId, Score score);
    String deleteScore(Integer scoreId);
    List<Score> searchScore(Score score);
}
