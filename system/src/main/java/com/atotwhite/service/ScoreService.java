package com.atotwhite.service;

import com.atotwhite.domain.Score;

import java.sql.SQLException;
import java.util.List;

public interface ScoreService {
    String addScore(Score score);
    List<Score> showAllScore();
    String updateScore(int id, Score score);
    String deleteScore(int id);
    List<Score> searchScore(Score score);
}
