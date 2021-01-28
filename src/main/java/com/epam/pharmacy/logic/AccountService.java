package com.epam.pharmacy.logic;

import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Prescription;

import java.util.Optional;

public interface AccountService {

    Optional<Account> authenticate(String login, String password) throws ServiceException;

    long updateBalance(Double balance,Long id) throws Exception;
}
