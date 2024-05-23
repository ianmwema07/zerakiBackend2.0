package com.zeraki.zeraki.controllers;


import com.zeraki.zeraki.Entities.AppUser;
import com.zeraki.zeraki.Entities.Lesson;
import com.zeraki.zeraki.responses.CustomResponse;
import com.zeraki.zeraki.services.LessonService;
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
@RequestMapping("/api/lesson")
public class LessonController {

    Logger logger = LoggerFactory.getLogger(LessonController.class);
    @Autowired
    LessonService lessonService;

    //Create lesson endpoint
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createUser(@RequestBody Lesson lesson){
        logger.info("create lesson request :: " + lesson.toString());
        try{
            Lesson lesson1 = lessonService.createLesson(lesson);
            CustomResponse customResponse = new CustomResponse("Lesson Created Successfully",lesson1);
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.CREATED);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update lesson
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomResponse> updateUser(@PathVariable Long id, @RequestBody Lesson lesson){
        logger.info("lesson update request :: " + lesson.toString());
        try{
            Lesson lesson1 = lessonService.upDateLesson(id, lesson);
            CustomResponse customResponse = new CustomResponse("Lesson updated Successfully",lesson1);
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.OK);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete lesson
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<CustomResponse> deleteLesson(@PathVariable Long id, @RequestBody Lesson lesson){
        logger.info("delete lesson  request :: " + lesson.toString());
        try{
            lessonService.deleteLesson(id);
            CustomResponse customResponse = new CustomResponse("lesson deleted Successfully","lesson Deleted");
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.OK);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
