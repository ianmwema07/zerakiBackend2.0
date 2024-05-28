package com.zeraki.zeraki.Logic;

import com.zeraki.zeraki.Entities.AppUser;
import com.zeraki.zeraki.Entities.Lesson;
import com.zeraki.zeraki.Entities.UserProgress;
import com.zeraki.zeraki.services.AppUserService;
import com.zeraki.zeraki.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    public List<UserProgress> addRemarks(List<UserProgress> userProgressList){
        List<UserProgress> ammendedList = new ArrayList<>();
        for(UserProgress userProgress : userProgressList){
            if(userProgress.getMarks() >= 40 && userProgress.getMarks() <= 50){
                userProgress.setRemarks("poor");
            } else if (userProgress.getMarks() >= 51 && userProgress.getMarks() <= 60) {
                userProgress.setRemarks("Fair");
            } else if (userProgress.getMarks() >= 61 && userProgress.getMarks() <= 70) {
                userProgress.setRemarks("Good");
            } else if (userProgress.getMarks() >= 71 && userProgress.getMarks() <= 80) {
                userProgress.setRemarks("Very Good");
            } else if (userProgress.getMarks() >= 80 && userProgress.getMarks() <= 100) {
                userProgress.setRemarks("Excellent");
            }

            ammendedList.add(userProgress);
        }
        return ammendedList;

    }



}




    //Assigning a score to a user.
