package com.zeraki.zeraki.Entities;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.Immutable;


@Immutable
@Entity

@SqlResultSetMapping(
        name = "UserProgressMapping",
        entities = @EntityResult(
                entityClass = UserProgress.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "userName", column = "user_name"),
                        @FieldResult(name = "exerciseName", column = "exercise_name"),
                        @FieldResult(name = "marks", column = "marks"),
                        @FieldResult(name = "remarks", column = "remarks"),
                        @FieldResult(name = "userId", column = "user_id")
                        // map other fields accordingly
                }
        )
)
public class UserProgress {

    @Id
    @Column(name = "id")
    Long id;

    Long userId;

    public UserProgress(int i) {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Long getMarks() {
        return marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    String userName;
    String exerciseName;
    Long marks;
    String remarks;
}
