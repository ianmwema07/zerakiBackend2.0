package com.zeraki.zeraki.services;

import com.zeraki.zeraki.Entities.UserProgress;

import java.util.List;

public interface UserProgressService {
    List<UserProgress> findAllUserProgress();

    List<UserProgress> findAllByUserId(Long id);
}
