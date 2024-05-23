package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.Exercise;
import com.zeraki.zeraki.responses.CustomResponse;
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
@RequestMapping("/api/exercise")
public class ExerciseController {

    Logger logger = LoggerFactory.getLogger(ExerciseController.class);
    @Autowired
    ExerciseService ExerciseService;

    //Create Exercise endpoint
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createUser(@RequestBody Exercise exercise){
        logger.info("create Exercise request :: " + exercise.toString());
        try{
            Exercise Exercise1 = ExerciseService.createExercise(exercise);
            CustomResponse customResponse = new CustomResponse("Exercise Created Successfully",Exercise1);
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.CREATED);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update Exercise
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomResponse> updateUser(@PathVariable Long id, @RequestBody Exercise exercise){
        logger.info("Exercise update request :: " + exercise.toString());
        try{
            Exercise Exercise1 = ExerciseService.upDateExercise(id, exercise);
            CustomResponse customResponse = new CustomResponse("Exercise updated Successfully",Exercise1);
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.OK);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Exercise
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<CustomResponse> deleteExercise(@PathVariable Long id, @RequestBody Exercise exercise){
        logger.info("delete Exercise  request :: " + exercise.toString());
        try{
            ExerciseService.deleteExercise(id);
            CustomResponse customResponse = new CustomResponse("Exercise deleted Successfully","Exercise Deleted");
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.OK);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
