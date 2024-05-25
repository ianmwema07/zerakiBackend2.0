package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.AppUser;
import com.zeraki.zeraki.Entities.AppUserLesson;
import com.zeraki.zeraki.auth.utils.JwtTokenUtil;
import com.zeraki.zeraki.responses.CustomResponse;
import com.zeraki.zeraki.services.AppUserLessonService;
import com.zeraki.zeraki.services.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class AppUserController {
    Logger logger = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    AppUserService appUserService;

    @Autowired
    AppUserLessonService appUserLessonService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Create user endpoint
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createUser(@RequestHeader(value = "Authorization", required = false)String token, @RequestBody AppUser appUser){
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            logger.info("create user request :: " + appUser.toString());
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")){
            try {
                String hashedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
                appUser.setPassword(hashedPassword);
                AppUser appUser1 = appUserService.createUser(appUser);
                CustomResponse customResponse = new CustomResponse("User Created Successfully", "User Created");
                return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);
            } catch (Exception exception) {
                logger.error("Exception has occurred :: " + exception.getMessage());
                CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            }else{
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "An error has occured");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }

        }
    }

    //update user
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomResponse> updateUser(@PathVariable Long id,@RequestHeader(value = "Authorization", required = false)String token, @RequestBody AppUser appUser){
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            logger.info("create user request :: " + appUser.toString());
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                logger.info("user update request :: " + appUser.toString());
                try {
                    AppUser appUser1 = appUserService.upDateUser(id, appUser);
                    CustomResponse customResponse = new CustomResponse("User updated Successfully", "User updated Successfully");
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else{
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "Un authorized token");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
            }

    }

    //Delete user
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable Long id,@RequestHeader(value = "Authorization", required = false)String token, @RequestBody AppUser appUser){
        logger.info("delete user  request :: " + appUser.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            logger.info("create user request :: " + appUser.toString());
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                logger.info("user update request :: " + appUser.toString());
                try {
                    appUserService.deleteUser(id);
                    CustomResponse customResponse = new CustomResponse("User deleted Successfully", "User Deleted");
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "An error has occured");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else{
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "Un authorized token");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Assing A user a lesson
    @RequestMapping(value = "/assignlesson",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> assignLesson( @RequestBody AppUserLesson appUserLesson, @RequestHeader(value = "Authorization", required = false)String token){
        logger.info("Assigning a user a lesson :: " + appUserLesson.toString());
        logger.info("delete user  request :: " + appUserLesson.toString());
        if (token == null) {
            logger.error("The token is empty :: ");
            CustomResponse customResponse = new CustomResponse("Token is required to proceed",HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.UNAUTHORIZED);
        } else {
            logger.info("create user request :: " + appUserLesson.toString());
            String realToken = token.substring(7);
            String tokenCheckResult = jwtTokenUtil.validateToken(realToken);
            if (tokenCheckResult.equalsIgnoreCase("valid")) {
                logger.info("user update request :: " + appUserLesson.toString());
                try {
                    AppUserLesson appUserLesson1 = appUserLessonService.createAppUserLesson(appUserLesson);
                    CustomResponse customResponse = new CustomResponse("Lesson Assigned Successfully", "lesson Assigned Successfully");
                    logger.info("Lesson assigned");
                    return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);
                } catch (Exception exception) {
                    logger.error("Exception has occurred :: " + exception.getMessage());
                    CustomResponse errorResponse = new CustomResponse(exception.getMessage(), "Error while assigning lesson");
                    return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                CustomResponse errorResponse = new CustomResponse(tokenCheckResult, "Un authorized token");
                return new ResponseEntity<CustomResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Updating that a user has completed a certain lesson
//    @RequestMapping(value = "/completelesson",method = RequestMethod.PUT)
//    public ResponseEntity<CustomResponse> completeLesson( @RequestBody AppUserLesson appUserLesson){
//        logger.info("Updating that a user has completed a lesson :: " + appUserLesson.toString());
//        try{
//            AppUserLesson appUserLesson1 = appUserLessonService.createAppUserLesson(appUserLesson);
//            CustomResponse customResponse = new CustomResponse("User deleted Successfully",appUserLesson1);
//            logger.info("Lesson assigned");
//            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.OK);
//        }catch (Exception exception){
//            logger.error("Exception has occurred :: " + exception.getMessage());
//            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"Error while assigning lesson");
//            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
