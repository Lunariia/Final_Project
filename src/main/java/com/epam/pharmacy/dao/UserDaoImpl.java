package com.epam.pharmacy.dao;

import com.epam.pharmacy.mapper.RowMapper;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.mapper.AccountMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<Account> implements UserDao {

    private static final String GET_USERS_BY_CREDENTIALS = "SELECT * FROM Users WHERE login = ? AND password = SHA!(?)";

    public UserDaoImpl(Connection connection, RowMapper<Account> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public Optional<Account> findByCredentials(String login, String password) throws DaoException {
        List<Account> users = executeQuery(GET_USERS_BY_CREDENTIALS, new AccountMapper(), login, password);
        if (users.size() == 1) {
            return Optional.of(users.get(0));
        } else {
            return Optional.empty();
        }
    }
}
