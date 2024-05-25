package com.zeraki.zeraki.controllers;


import com.zeraki.zeraki.Entities.AppUser;
import com.zeraki.zeraki.Entities.Lesson;
import com.zeraki.zeraki.auth.utils.JwtTokenUtil;
import com.zeraki.zeraki.responses.CustomResponse;
import com.zeraki.zeraki.services.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/lesson")
public class LessonController {

    Logger logger = LoggerFactory.getLogger(LessonController.class);
    @Autowired
    LessonService lessonService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Create lesson endpoint
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createUser(@RequestHeader(value = "Authorization", required = false)String token, @RequestBody Lesson lesson){
        logger.info("create lesson request :: " + lesson.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {
                    Lesson lesson1 = lessonService.createLesson(lesson);
                    CustomResponse customResponse = new CustomResponse("Lesson Created Successfully", lesson1);
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

    //update lesson
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomResponse> updateLesson(@RequestHeader(value = "Authorization", required = false)String token,@PathVariable Long id, @RequestBody Lesson lesson){
        logger.info("lesson update request :: " + lesson.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {
                    Lesson lesson1 = lessonService.upDateLesson(id, lesson);
                    CustomResponse customResponse = new CustomResponse("Lesson updated Successfully", lesson1);
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "An error has occured");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Delete lesson
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<CustomResponse> deleteLesson(@RequestHeader(value = "Authorization", required = false)String token, @PathVariable Long id, @RequestBody Lesson lesson) {
        logger.info("delete lesson  request :: " + lesson.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed", HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                try {
                    lessonService.deleteLesson(id);
                    CustomResponse customResponse = new CustomResponse("lesson deleted Successfully", "lesson Deleted");
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "An error has occured");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
