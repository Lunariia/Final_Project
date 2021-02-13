package com.epam.pharmacy.logic.account;

import com.epam.pharmacy.logic.exception.ServiceException;
import com.epam.pharmacy.model.entity.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> authenticate(String login, String password) throws ServiceException;

    void updateBalance(Double balance,Long id) throws Exception;
}
