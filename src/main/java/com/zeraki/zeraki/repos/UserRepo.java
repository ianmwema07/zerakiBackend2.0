package com.zeraki.zeraki.repos;

import com.zeraki.zeraki.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
}
