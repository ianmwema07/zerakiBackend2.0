package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.Exercise;
import com.zeraki.zeraki.Entities.ExerciseScore;
import com.zeraki.zeraki.responses.CustomResponse;
import com.zeraki.zeraki.services.ExerciseScoreService;
import com.zeraki.zeraki.services.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/score")
public class ExerciseScoreController {
    Logger logger = LoggerFactory.getLogger(ExerciseController.class);
    @Autowired
    ExerciseScoreService exerciseScoreService;

    //Create Exercise endpoint
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createUser(@RequestBody ExerciseScore exerciseScore){
        logger.info("create ExerciseScore request :: " + exerciseScore.toString());

        //Check function goes here
        try{
            ExerciseScore exerciseScore1 = exerciseScoreService.createExerciseScore(exerciseScore);
            CustomResponse customResponse = new CustomResponse("Exercise Created Successfully",exerciseScore1);
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.CREATED);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update Exercise
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomResponse> updateUser(@PathVariable Long id, @RequestBody ExerciseScore exerciseScore){
        logger.info("Exercise Score update request :: " + exerciseScore.toString());
        try{
            ExerciseScore exerciseScore1 = exerciseScoreService.upDateExerciseScore(id, exerciseScore);
            CustomResponse customResponse = new CustomResponse("Exercise updated Successfully",exerciseScore1);
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.OK);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
