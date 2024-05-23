package com.zeraki.zeraki.serviceimpl;

import com.zeraki.zeraki.Entities.Lesson;
import com.zeraki.zeraki.repos.LessonRepo;
import com.zeraki.zeraki.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepo lessonRepo;
    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepo.save(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepo.findAll();
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepo.deleteById(id);
    }

    @Override
    public Lesson upDateLesson(Long id, Lesson lessonDetails) {
        Lesson lesson = lessonRepo.findById(id).get();
        lesson.setName(lessonDetails.getName());
        return lessonRepo.save(lesson);
    }
}
