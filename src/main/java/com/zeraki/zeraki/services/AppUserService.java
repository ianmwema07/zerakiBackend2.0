package com.zeraki.zeraki.services;

import com.zeraki.zeraki.Entities.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AppUserService {
    //Create
    AppUser createUser(AppUser appUser);

    //Get all users
    List<AppUser> getAllUsers();

    //delete Specific user
    void  deleteUser(Long id);

    //update Specific user
    AppUser upDateUser(Long id, AppUser appUserDetails);

}
