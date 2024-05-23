package com.zeraki.zeraki.services;

import com.zeraki.zeraki.Entities.AppUserLesson;

import java.util.List;

public interface  AppUserLessonService{
    //Create
    AppUserLesson createAppUserLesson(AppUserLesson appUserLesson);

    //Get all AppUserLessons
    List<AppUserLesson> getAllAppUserLessons();

    //delete Specific AppUserLesson
    void  deleteAppUserLesson(Long id);

    //update Specific AppUserLesson
    AppUserLesson  upDateAppUserLesson(Long id,AppUserLesson appUserLessonDetails);
}
