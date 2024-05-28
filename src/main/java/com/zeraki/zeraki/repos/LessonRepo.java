package com.zeraki.zeraki.repos;

import com.zeraki.zeraki.Entities.Lesson;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
    Lesson findLessonById(Long id);
}
