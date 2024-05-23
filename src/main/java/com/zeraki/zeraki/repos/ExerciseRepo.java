package com.zeraki.zeraki.repos;

import com.zeraki.zeraki.Entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepo extends JpaRepository<Exercise,Long> {
}
