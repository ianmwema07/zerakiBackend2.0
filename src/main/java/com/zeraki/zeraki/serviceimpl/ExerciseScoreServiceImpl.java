package com.zeraki.zeraki.serviceimpl;

import com.zeraki.zeraki.Entities.ExerciseScore;
import com.zeraki.zeraki.repos.ExerciseRepo;
import com.zeraki.zeraki.repos.ExerciseScoreRepo;
import com.zeraki.zeraki.services.ExerciseScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseScoreServiceImpl  implements ExerciseScoreService {

    @Autowired
    ExerciseScoreRepo exerciseScoreRepo;
    @Override
    public ExerciseScore createExerciseScore(ExerciseScore exerciseScore) {
        return exerciseScoreRepo.save(exerciseScore);
    }

    @Override
    public List<ExerciseScore> getAllExerciseScores() {
        return exerciseScoreRepo.findAll();
    }

    @Override
    public ExerciseScore upDateExerciseScore(Long id, ExerciseScore exerciseScoreDetails) {
        ExerciseScore exerciseScore = exerciseScoreRepo.findById(id).get();
        exerciseScore.setMarks(exerciseScoreDetails.getMarks());
        exerciseScore.setRemarks(exerciseScore.getRemarks());
        return exerciseScoreRepo.save(exerciseScore);
    }
}
