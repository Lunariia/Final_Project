package com.epam.pharmacy.dao.manager;

import com.epam.pharmacy.dao.DaoException;
import com.epam.pharmacy.dao.UserDao;
import com.epam.pharmacy.dao.UserDaoImpl;
import com.epam.pharmacy.mapper.AccountMapper;
import com.epam.pharmacy.mapper.RowMapper;
import com.epam.pharmacy.model.entity.Account;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoConnectionManagerImpl implements DaoConnectionManager{

    private final Connection connection;

    public DaoConnectionManagerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws DaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public UserDao createAccountDao() {
        RowMapper<Account> rowMapper = new AccountMapper();
        return new UserDaoImpl(connection, rowMapper);
    }
}
