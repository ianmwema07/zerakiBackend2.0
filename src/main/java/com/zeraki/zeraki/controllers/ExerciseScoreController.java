package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.ExerciseScore;
import com.zeraki.zeraki.Logic.ExerciseUserExistence;
import com.zeraki.zeraki.auth.utils.JwtTokenUtil;
import com.zeraki.zeraki.responses.CustomResponse;
import com.zeraki.zeraki.services.ExerciseScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/score")
public class ExerciseScoreController {
    Logger logger = LoggerFactory.getLogger(ExerciseController.class);
    @Autowired
    ExerciseScoreService exerciseScoreService;



    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ExerciseUserExistence exerciseUserExistence;

    //Create Exercise endpoint
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createUser(@RequestHeader(value = "Authorization", required = false)String token,@RequestBody ExerciseScore exerciseScore){
        logger.info("create ExerciseScore request :: " + exerciseScore.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {

        //Check function goes here
        try{
            ExerciseScore exerciseScore1 = exerciseScoreService.createExerciseScore(exerciseScore);
            CustomResponse customResponse = new CustomResponse("Exercise Created Successfully","CREATED SUCCESSFULLY");
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.CREATED);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
            }
            else {
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "Unauthorized request ");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //update Exercise
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomResponse> updateUser(@RequestHeader(value = "Authorization", required = false)String token, @PathVariable Long id, @RequestBody ExerciseScore exerciseScore){
        logger.info("Exercise Score update request :: " + exerciseScore.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {
                    ExerciseScore exerciseScore1 = exerciseScoreService.upDateExerciseScore(id, exerciseScore);
                    CustomResponse customResponse = new CustomResponse("Exercise updated Successfully", "UPDATED SUCCESSFULLY");
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            else {
                    CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "Unauthorized request ");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
                }
            }

    }

}
