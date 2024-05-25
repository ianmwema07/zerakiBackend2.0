package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.Exercise;
import com.zeraki.zeraki.auth.utils.JwtTokenUtil;
import com.zeraki.zeraki.responses.CustomResponse;
import com.zeraki.zeraki.services.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/exercise")
public class ExerciseController {

    Logger logger = LoggerFactory.getLogger(ExerciseController.class);
    @Autowired
    ExerciseService ExerciseService;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Create Exercise endpoint
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createUser(@RequestHeader(value = "Authorization", required = false)String token, @RequestBody Exercise exercise){
        logger.info("create Exercise request :: " + exercise.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {
                    Exercise Exercise1 = ExerciseService.createExercise(exercise);
                    CustomResponse customResponse = new CustomResponse("Exercise Created Successfully", Exercise1);
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "Unauthorized request ");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //update Exercise
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomResponse> updateUser(@RequestHeader(value = "Authorization", required = false)String token,@PathVariable Long id, @RequestBody Exercise exercise){
        logger.info("Exercise update request :: " + exercise.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {
                    Exercise Exercise1 = ExerciseService.upDateExercise(id, exercise);
                    CustomResponse customResponse = new CustomResponse("Exercise updated Successfully", Exercise1);
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "Unauthorized request ");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Delete Exercise
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<CustomResponse> deleteExercise(@RequestHeader(value = "Authorization", required = false)String token, @PathVariable Long id, @RequestBody Exercise exercise){
        logger.info("delete Exercise  request :: " + exercise.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {
                    ExerciseService.deleteExercise(id);
                    CustomResponse customResponse = new CustomResponse("Exercise deleted Successfully", "Exercise Deleted");
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "Unauthorized request ");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
