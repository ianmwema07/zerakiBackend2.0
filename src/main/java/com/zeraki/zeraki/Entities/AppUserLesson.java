package com.zeraki.zeraki.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This acts as a pivot table for assigning lessons to students.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUserLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long userId;
    Long LessonId;
    String CompletionStatus;
}
