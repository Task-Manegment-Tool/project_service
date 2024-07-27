package com.blue.project_service.services;


import com.blue.project_service.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserService {
    @GetMapping("/public/getuser/{userId}")
    public ResponseEntity<User> findUserbyId(@PathVariable long userId);


}
