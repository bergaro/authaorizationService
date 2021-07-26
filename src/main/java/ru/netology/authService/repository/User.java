package ru.netology.authService.repository;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class User {

    @Size(min = 3, max = 10)
    private String name;

    @Size(min = 4, max = 10)
    private String password;
    private List<Authorities> userAuthorities  = new ArrayList<>(6);

    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Authorities> getUserAuthorities() {
        return userAuthorities;
    }

    public User setUserAuthorities(Authorities authorities) {
        userAuthorities.add(authorities);
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
