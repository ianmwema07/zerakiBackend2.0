package com.zeraki.zeraki.serviceimpl;

import com.zeraki.zeraki.Entities.AppUserLesson;
import com.zeraki.zeraki.repos.AppUserLessonRepo;
import com.zeraki.zeraki.services.AppUserLessonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppUserLessonServiceImpl implements AppUserLessonService {

    @Autowired
    AppUserLessonRepo appUserLessonRepo;
    @Override
    public AppUserLesson createAppUserLesson(AppUserLesson appUserLesson) {
        return appUserLessonRepo.save(appUserLesson);
    }

    @Override
    public List<AppUserLesson> getAllAppUserLessons() {
        return appUserLessonRepo.findAll();
    }

    @Override
    public void deleteAppUserLesson(Long id) {
        appUserLessonRepo.deleteById(id);
    }

    @Override
    public AppUserLesson upDateAppUserLesson(Long id, AppUserLesson appUserLessonDetails) {
        AppUserLesson appUserLesson = appUserLessonRepo.findById(id).get();
        appUserLesson.setLessonId(appUserLessonDetails.getLessonId());
        appUserLesson.setUserId(appUserLesson.getUserId());
        return appUserLessonRepo.save(appUserLesson);
    }
}
