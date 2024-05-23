package com.zeraki.zeraki.serviceimpl;

import com.zeraki.zeraki.Entities.Lesson;
import com.zeraki.zeraki.Entities.LessonExercise;
import com.zeraki.zeraki.repos.LessonExerciseRepo;
import com.zeraki.zeraki.services.LessonExerciseService;
import com.zeraki.zeraki.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonExerciseServiceImpl implements LessonExerciseService {

    @Autowired
    LessonExerciseRepo lessonExerciseRepo;
    @Override
    public LessonExercise createLessonExercise(LessonExercise lessonExercise) {
        return lessonExerciseRepo.save(lessonExercise);
    }

    @Override
    public List<LessonExercise> getAllLessonExercises() {
        return lessonExerciseRepo.findAll();
    }

    @Override
    public void deleteLessonExercise(Long id) {
        lessonExerciseRepo.deleteById(id);
    }

    @Override
    public LessonExercise upDateLessonExercise(Long id, LessonExercise lessonExerciseDetails) {
        LessonExercise lessonExercise = lessonExerciseRepo.findById(id).get();
        lessonExercise.setLessonId(lessonExerciseDetails.getLessonId());
        lessonExercise.setExerciseId(lessonExerciseDetails.getExerciseId());
        return lessonExerciseRepo.save(lessonExercise);
    }
}
