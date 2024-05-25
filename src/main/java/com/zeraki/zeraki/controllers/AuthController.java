package com.zeraki.zeraki.controllers;

import com.zeraki.zeraki.Entities.AppUser;
import com.zeraki.zeraki.auth.models.TokenResponse;
import com.zeraki.zeraki.auth.utils.JwtTokenUtil;
import com.zeraki.zeraki.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/auth",method = RequestMethod.POST)
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody AppUser appUser){
        String hashedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(hashedPassword);
        if(appUserService.createUser(appUser).getId()>0){
            return ResponseEntity.ok("User was saved");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not saved internal server error, please try again");
        }
    }

    @PostMapping("/generate-token")
    public ResponseEntity<Object> generateToken(@RequestBody TokenResponse tokenResponse){
        //Validate the user
        AppUser databaseUser = appUserService.findByUserName(tokenResponse.getUserName());
        if (databaseUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User, Doesn't exist");
        }
        if(new BCryptPasswordEncoder().matches(tokenResponse.getPassword(), databaseUser.getPassword())){
            String token = jwtTokenUtil.generateToken(tokenResponse.getToken());
            tokenResponse.setToken(token);
            tokenResponse.setExpiryDate("120 sec");
            return  ResponseEntity.ok(tokenResponse);
        }else {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password doesn't march. Verify");
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Object> validateToken(@RequestBody TokenResponse tokenResponse){
        return ResponseEntity.ok(jwtTokenUtil.validateToken(tokenResponse.getToken()));
    }
}
