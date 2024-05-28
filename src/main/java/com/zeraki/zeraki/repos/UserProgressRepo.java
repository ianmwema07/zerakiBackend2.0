package com.zeraki.zeraki.repos;

import com.zeraki.zeraki.Entities.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProgressRepo extends JpaRepository<UserProgress,Long> {
    List<UserProgress> findAllByUserId(Long id);
}
