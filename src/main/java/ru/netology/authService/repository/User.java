package ru.netology.authService.repository;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private List<Authorities> userAuthorities = new ArrayList<>(6);


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authorities> getUserAuthorities() {
        return userAuthorities;
    }

    public User setUserAuthorities(Authorities authorities) {
        userAuthorities.add(authorities);
        return this;
    }
}
