package com.example.Fit_App.controller;

import com.example.Fit_App.domain.dto.UserDTOForm;
import com.example.Fit_App.domain.dto.UserDTOView;
import com.example.Fit_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")


public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTOView> doRegister(@RequestBody UserDTOForm dtoForm) {
        UserDTOView responseBody = userService.register(dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);

    }
    @GetMapping("/login")
    public ResponseEntity<UserDTOView> doLogin(@RequestParam String email, @RequestParam String password) {
        UserDTOView responseBody = userService.login(email, password);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    @GetMapping("/authenticate")
    private ResponseEntity<UserDTOView> authentication(@RequestBody UserDTOForm dtoForm) {
        UserDTOView responseBody = userService.authenticate(dtoForm);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
}
