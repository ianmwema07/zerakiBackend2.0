package com.zeraki.zeraki.services;

import com.zeraki.zeraki.Entities.Lesson;

import java.util.List;

public interface LessonService {
    //Create
    Lesson createLesson(Lesson lesson);

    //Get all lessons
    List<Lesson> getAllLessons();

    //delete Specific lesson
    void  deleteLesson(Long id);

    //update Specific lesson
    Lesson  upDateLesson(Long id,Lesson lessonDetails);

    //find lesson by id
    Lesson findLessonById(Long id);
}
