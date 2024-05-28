package com.zeraki.zeraki.serviceimpl;

import com.zeraki.zeraki.Entities.UserProgress;
import com.zeraki.zeraki.repos.UserProgressRepo;
import com.zeraki.zeraki.services.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProgressServiceImpl implements UserProgressService {
    @Autowired
    UserProgressRepo userProgressRepo;
    @Override
    public List<UserProgress> findAllUserProgress() {
      return userProgressRepo.findAll();
    }

    @Override
    public List<UserProgress> findAllByUserId(Long id) {
        return userProgressRepo.findAllByUserId(id);
    }


}
