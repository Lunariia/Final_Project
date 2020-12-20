package com.epam.pharmacy.command;

import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.logic.ServiceException;
import com.epam.pharmacy.login.AccountService;
import com.epam.pharmacy.login.AccountServiceImpl;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.pages.PageCommand;

import java.util.Optional;

public class CommandFactory {

    public static Command create(String command) {
        switch (command) {
            case "login":{
                DaoConnectionManagerFactory factory = new DaoConnectionManagerFactory();
                AccountService accountService = new AccountServiceImpl(factory);
                return new LoginCommand(accountService);
            }
            case "signIn":
            case "aboutUs":
            case "contacts":
            case "departments":
                return new PageCommand(command);
            default:
                throw new IllegalArgumentException();
        }
    }
}
