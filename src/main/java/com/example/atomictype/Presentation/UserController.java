package com.example.atomictype.Presentation;


import com.example.atomictype.Business.Entity.AUser;
import com.example.atomictype.Business.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController @RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/save")
    public ResponseEntity<AUser> saveUser(@RequestBody AUser user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("user/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<AUser>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

}
