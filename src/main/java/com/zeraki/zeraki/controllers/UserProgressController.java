package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.LessonExercise;
import com.zeraki.zeraki.auth.utils.JwtTokenUtil;
import com.zeraki.zeraki.responses.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserProgressController {

    Logger logger = LoggerFactory.getLogger(UserProgressController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/lesson/exercise",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> assignExerciseToLesson(@RequestHeader(value = "Authorization", required = false)String token, @RequestBody LessonExercise lessonExercise) {
        logger.info("delete lesson  request :: " + lessonExercise.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed", HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {

                    CustomResponse customResponse = new CustomResponse("exercise assigned Successfully", "exercise assigned successfully");
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
