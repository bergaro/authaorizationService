package ru.netology.authService.service;

import org.springframework.stereotype.Service;
import ru.netology.authService.exception.InvalidCredentials;
import ru.netology.authService.exception.UnauthorizedUser;
import ru.netology.authService.repository.Authorities;
import ru.netology.authService.repository.User;
import ru.netology.authService.repository.UserRepository;

import java.util.List;

@Service
public class AuthService {
    UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User setNewUser(User user) {
        return userRepository.setNewUser(user);
    }

    public List<Authorities> getAuthorities(User user) {
        if (isEmpty(user.getName()) || isEmpty(user.getPassword())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user.getName(), user.getPassword());
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
