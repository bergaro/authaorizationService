package ru.netology.authService.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.authService.repository.Authorities;
import ru.netology.authService.repository.User;
import ru.netology.authService.service.AuthService;

import java.util.List;

@RestController
public class AuthController {
    AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user,
                                            @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @PostMapping("/reg")
    public User setNewUser(@RequestBody User user) {
        return service.setNewUser(user);
    }
}
