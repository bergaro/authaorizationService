package ru.netology.authService.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.authService.conigurer.UserResolver;
import ru.netology.authService.repository.Authorities;
import ru.netology.authService.repository.User;
import ru.netology.authService.service.AuthService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthController {
    AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@UserResolver @Valid User user) {
        return service.getAuthorities(user);
    }

    @PostMapping("/reg")
    public User setNewUser(@RequestBody @Valid User user) {
        return service.setNewUser(user);
    }
}
