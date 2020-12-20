package com.epam.pharmacy.login;

public class LoginService {

    public boolean login(String user, String password) {
        return "admin@mail.ru".equals(user) && "admin".equals(password);
    }
}
