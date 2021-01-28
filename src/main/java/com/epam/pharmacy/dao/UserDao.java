package com.epam.pharmacy.dao;

import com.epam.pharmacy.model.entity.Account;

import java.util.Optional;

public interface UserDao{

    Optional<Account> findByCredentials(String login,String password) throws DaoException;

    Long updateBalance(Double balance,Long id) throws DaoException;
}
