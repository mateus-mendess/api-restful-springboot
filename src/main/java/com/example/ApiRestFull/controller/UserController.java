package com.example.ApiRestFull.controller;

import com.example.ApiRestFull.domain.service.UserService;
import com.example.ApiRestFull.dto.request.RequestUser;
import com.example.ApiRestFull.dto.response.ResponseUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAllUsers(RequestUser requestUser) {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity createUser(RequestUser requestUser) {
        ResponseUser responseUser = userService.save(requestUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(responseUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody RequestUser requestUser, @PathVariable Long id) {
        userService.updateUser(requestUser, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
