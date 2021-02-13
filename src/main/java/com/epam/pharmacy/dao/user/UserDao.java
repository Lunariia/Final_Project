package com.epam.pharmacy.dao.user;

import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.model.entity.Account;

import java.util.Optional;

public interface UserDao{

    Optional<Account> findByCredentials(String login,String password) throws DaoException;

    void updateBalance(Double balance,Long id) throws DaoException;
}
