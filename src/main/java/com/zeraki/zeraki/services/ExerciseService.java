package com.zeraki.zeraki.services;

import com.zeraki.zeraki.Entities.Exercise;

import java.util.List;

public interface ExerciseService {
    //Create
    Exercise createExercise(Exercise exercise);

    //Get all Exercises
    List<Exercise> getAllExercises();

    //delete Specific Exercise
    void  deleteExercise(Long id);

    //update Specific Exercise
    Exercise  upDateExercise(Long id,Exercise exerciseDetails);
}
