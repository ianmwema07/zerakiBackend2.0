package com.zeraki.zeraki.serviceimpl;

import com.zeraki.zeraki.Entities.Exercise;
import com.zeraki.zeraki.repos.ExerciseRepo;
import com.zeraki.zeraki.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseRepo exerciseRepo;
    @Override
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepo.deleteById(id);
    }

    @Override
    public Exercise upDateExercise(Long id, Exercise exerciseDetails) {
        Exercise exercise = exerciseRepo.findById(id).get();
        exercise.setName(exerciseDetails.getName());
        exercise.setMarks(exerciseDetails.getMarks());
        exercise.setRemarks(exerciseDetails.getRemarks());
        return exerciseRepo.save(exercise);
    }
}
