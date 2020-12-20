package com.epam.pharmacy.login;

import com.epam.pharmacy.logic.ServiceException;
import com.epam.pharmacy.model.entity.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> authenticate(String login, String password) throws ServiceException;


}
