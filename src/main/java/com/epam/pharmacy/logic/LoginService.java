package com.epam.pharmacy.logic;

public class LoginService {

    public boolean login(String login, String password) {
        return "admin".equals(login) && "admin".equals(password);
    }
}
