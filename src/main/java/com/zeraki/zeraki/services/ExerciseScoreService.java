package com.zeraki.zeraki.services;

import com.zeraki.zeraki.Entities.ExerciseScore;

import java.util.List;

public interface ExerciseScoreService {
    ExerciseScore createExerciseScore(ExerciseScore exerciseScore);

    //Get all exerciseScores
    List<ExerciseScore> getAllExerciseScores();



    //update Specific exerciseScore
    ExerciseScore upDateExerciseScore(Long id, ExerciseScore exerciseScoreDetails);
}
