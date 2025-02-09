package com.thinkify.event.registration.system.authentication.user.repository;

import com.thinkify.event.registration.system.authentication.user.userDTO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
