package com.zeraki.zeraki.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This acts as a pivot table for assigning exercises to Lessons
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LessonExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long lessonId;
    Long exerciseId;
}
