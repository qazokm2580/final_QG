package com.hzyj233.pojo;

import java.util.*;

public class ScoreManager {
    private Map<String, Map<String, Integer>> scores;

    public ScoreManager() {
        scores = new HashMap<>();
    }

    public void addScore(String studentName, String questionId, int score) {
        Map<String, Integer> studentScores = scores.get(studentName);
        if (studentScores == null) {
            studentScores = new HashMap<>();
            scores.put(studentName, studentScores);
        }
        studentScores.put(questionId, score);
    }

    public Integer getScore(String studentName, String questionId) {
        Map<String, Integer> studentScores = scores.get(studentName);
        if (studentScores == null) {
            return null;
        }
        return studentScores.get(questionId);
    }
}
