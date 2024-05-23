package com.zeraki.zeraki.services;

import com.zeraki.zeraki.Entities.LessonExercise;

import java.util.List;

public interface LessonExerciseService {
    //Create
    LessonExercise createLessonExercise(LessonExercise lessonExercise);

    //Get all LessonExercises
    List<LessonExercise> getAllLessonExercises();

    //delete Specific LessonExercise
    void  deleteLessonExercise(Long id);

    //update Specific LessonExercise
    LessonExercise  upDateLessonExercise(Long id,LessonExercise lessonExerciseDetails);
}
