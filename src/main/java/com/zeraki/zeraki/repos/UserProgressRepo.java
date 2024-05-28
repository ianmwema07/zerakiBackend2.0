package com.zeraki.zeraki.repos;

import com.zeraki.zeraki.Entities.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProgressRepo extends JpaRepository<Long, UserProgress> {
    UserProgress findById(Long i);
}
