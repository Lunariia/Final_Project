package com.epam.pharmacy.dao.manager;

import com.epam.pharmacy.connection.ConnectionPool;
import com.epam.pharmacy.connection.ConnectionPoolException;
import com.epam.pharmacy.dao.DaoException;

import java.sql.Connection;

public class DaoConnectionManagerFactory {

    public DaoConnectionManager create() throws DaoException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = connectionPool.getConnection();
            return new DaoConnectionManagerImpl(connection);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }
}
