package com.epam.pharmacy.logic.mapper;

import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {

    private static final String ACCOUNT_ID = "id";
    private static final String ACCOUNT_LOGIN = "login";
    private static final String ACCOUNT_PASSWORD = "password";
    private static final String ACCOUNT_ROLE = "role";
    private static final String ACCOUNT_FIRST_NAME = "first_name";
    private static final String ACCOUNT_LAST_NAME = "last_name";
    private static final String ACCOUNT_BALANCE = "balance";

    @Override
    public Account map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ACCOUNT_ID);
        String login = resultSet.getString(ACCOUNT_LOGIN);
        String password = resultSet.getString(ACCOUNT_PASSWORD);
        String roleValue = resultSet.getString(ACCOUNT_ROLE);
        Role role = Role.valueOf(roleValue);
        String firstName = resultSet.getString(ACCOUNT_FIRST_NAME);
        String lastName = resultSet.getString(ACCOUNT_LAST_NAME);
        long balance = resultSet.getLong(ACCOUNT_BALANCE);
        return new Account(id, login, password, role, firstName, lastName, balance);
    }
}
