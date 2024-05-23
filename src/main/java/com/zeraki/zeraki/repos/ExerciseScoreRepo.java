package com.zeraki.zeraki.repos;

import com.zeraki.zeraki.Entities.ExerciseScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseScoreRepo extends JpaRepository<ExerciseScore,Long> {
}
