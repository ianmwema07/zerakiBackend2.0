package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.LessonExercise;
import com.zeraki.zeraki.Entities.UserProgress;
import com.zeraki.zeraki.Logic.Logic;
import com.zeraki.zeraki.auth.utils.JwtTokenUtil;
import com.zeraki.zeraki.responses.CustomResponse;
import com.zeraki.zeraki.services.UserProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/userprogress")
public class UserProgressController {

    Logger logger = LoggerFactory.getLogger(UserProgressController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserProgressService userProgressService;

    @Autowired
    private Logic logic;


    //Getting all the user progress
    @RequestMapping( value = "/getUserProgress/{userId}",method = RequestMethod.GET)
    public ResponseEntity<CustomResponse> getUserProgress(@PathVariable Long userId, @RequestHeader(value = "Authorization", required = false)String token, @RequestBody Object requestObject) {
        logger.info("delete lesson  request :: " + requestObject.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed", HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {
                   List<UserProgress> userProgressList =  userProgressService.findAllByUserId(userId);
                   List<UserProgress> ammendedList = logic.addRemarks(userProgressList);
                    CustomResponse customResponse = new CustomResponse("User's progress list fetched successfully", ammendedList);
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "Exercise was not assigned successfully");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "An error has occured");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
}
}
