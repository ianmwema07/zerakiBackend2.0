package com.zeraki.zeraki.Logic;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ExerciseUserExistence {
    private boolean userExists;
    private boolean exerciseExists;



    public boolean isUserExists(Long id) {
        return userExists;
    }

    public void setUserExists(boolean userExists) {
        this.userExists = userExists;
    }

    public boolean isExerciseExists(Long id) {
        return exerciseExists;
    }

    public void setExerciseExists(boolean exerciseExists) {
        this.exerciseExists = exerciseExists;
    }
}
