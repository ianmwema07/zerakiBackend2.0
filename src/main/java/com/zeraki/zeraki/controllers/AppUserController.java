package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.AppUser;
import com.zeraki.zeraki.responses.CustomResponse;
import com.zeraki.zeraki.services.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/user")
public class AppUserController {
    Logger logger = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    AppUserService appUserService;

    //Create user endpoint
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> createUser(@RequestBody AppUser appUser){
        logger.info("create user request :: " + appUser.toString());
        try{
            AppUser appUser1 = appUserService.createUser(appUser);
            CustomResponse customResponse = new CustomResponse("User Created Successfully",appUser1);
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.CREATED);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomResponse> updateUser(@PathVariable Long id, @RequestBody AppUser appUser){
        logger.info("user update request :: " + appUser.toString());
        try{
            AppUser appUser1 = appUserService.upDateUser(id, appUser);
            CustomResponse customResponse = new CustomResponse("User updated Successfully",appUser1);
            return new ResponseEntity<CustomResponse> (customResponse, HttpStatus.OK);
        }catch (Exception exception){
            logger.error("Exception has occurred :: " + exception.getMessage());
            CustomResponse errorResponse = new CustomResponse(exception.getMessage(),"An error has occured");
            return new ResponseEntity<CustomResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
