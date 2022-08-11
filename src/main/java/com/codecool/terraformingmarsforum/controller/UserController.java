package com.codecool.terraformingmarsforum.controller;


import com.codecool.terraformingmarsforum.model.User;
import com.codecool.terraformingmarsforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        int id = userService.createUser(user);
        return ResponseEntity.created(URI.create(String.format("api/comments/%d", id))).body(user);
    }

}

