package com.zeraki.zeraki.serviceimpl;

import com.zeraki.zeraki.Entities.AppUser;
import com.zeraki.zeraki.repos.UserRepo;
import com.zeraki.zeraki.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public AppUser createUser(AppUser appUser) {
        return userRepo.save(appUser);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public AppUser upDateUser(Long id, AppUser appUserDetails) {
        AppUser appUser = userRepo.findById(id).get();
        appUser.setUserName(appUserDetails.getUserName());
        appUser.setPassword(appUserDetails.getPassword());
        return userRepo.save(appUser);
    }

    @Override
    public AppUser findByUserName(String username) {
        return userRepo.findByUserName(username);
    }
}
