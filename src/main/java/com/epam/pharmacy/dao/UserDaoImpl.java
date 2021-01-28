package com.epam.pharmacy.dao;

import com.epam.pharmacy.logic.mapper.RowMapper;
import com.epam.pharmacy.model.entity.Account;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<Account> implements UserDao {

    private static final String GET_USERS_BY_CREDENTIALS = "SELECT id,login,password,role,first_name,last_name,balance" +
            " FROM Users WHERE login = ? AND password = SHA1(?)";
    private static final String UPDATE_USERS_BALANCE = "update users set balance = ? where id = ?";

    public UserDaoImpl(Connection connection, RowMapper<Account> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public Optional<Account> findByCredentials(String login, String password) throws DaoException {
        return selectSingle(GET_USERS_BY_CREDENTIALS, login, password);
    }

    @Override
    public Long updateBalance(Double balance,Long id) throws DaoException {
        updateSingle(UPDATE_USERS_BALANCE, balance,id);
        return id;
    }

    @Override
    public List<Account> getAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Account> getById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Long add(Account element) throws DaoException {
        return null;
    }

    @Override
    public void delete(long id) throws DaoException {

    }
}
