package com.epam.pharmacy.mapper;

import com.epam.pharmacy.model.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        long balance = resultSet.getLong("balance");
        return new Account(id, login, password, role, firstName, lastName, balance);
    }
}
