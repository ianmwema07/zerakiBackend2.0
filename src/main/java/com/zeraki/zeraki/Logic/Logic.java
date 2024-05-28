package com.zeraki.zeraki.Logic;

import com.zeraki.zeraki.Entities.AppUser;
import com.zeraki.zeraki.Entities.Lesson;
import com.zeraki.zeraki.services.AppUserService;
import com.zeraki.zeraki.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Logic {

    @Autowired
    AppUserService appUserService;

    @Autowired
    LessonService lessonService;

    @Autowired
    ExerciseUserExistence exerciseUserExistence;

    //Assigning a lesson to a user
    public boolean checkIfUserExists(Long id){
        AppUser appUser = appUserService.findById(id);
        return appUser != null;
    }

    //Assigning an Exercise to a lesson
    public boolean checkIfExerciseExists(Long id){
        Lesson lesson = lessonService.findLessonById(id);
        return lesson != null;
    }

    //Assigning an Exercise to a class
    public ExerciseUserExistence checkIfBothUserAndExerciseExist(Long userId, Long exerciseId){


        if(checkIfUserExists(userId)){
            exerciseUserExistence.setUserExists(true);
        } else {
            exerciseUserExistence.setExerciseExists(false);
        }

        if(checkIfExerciseExists(exerciseId)){
            exerciseUserExistence.setExerciseExists(true);
        } else {
            exerciseUserExistence.setExerciseExists(false);
        }

        return exerciseUserExistence;

    }


    //Adding remarks to each exercise




}




    //Assigning a score to a user.
