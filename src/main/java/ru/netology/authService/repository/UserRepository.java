package ru.netology.authService.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {
    private static final Map<String, User> repoMap = new ConcurrentHashMap<>();
    private static final AtomicInteger userId = new AtomicInteger(1);

    public List<Authorities> getUserAuthorities(String userName, String password) {
        for(User user : repoMap.values()) {
            if(user.getName().equals(userName) && user.getPassword().equals(password)) {
                return user.getUserAuthorities();
            }
        }
        return null;
    }

    public User setNewUser(User user) {
        user.setUserAuthorities(Authorities.READ).setUserAuthorities(Authorities.WRITE);
        int id = userId.getAndIncrement();
        repoMap.put(String.valueOf(id), user);
        return user;
    }
}
