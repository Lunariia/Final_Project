package com.epam.pharmacy.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final int POOL_SIZE = 10;
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private static boolean isReady = false;
    private static ConnectionPool instance = null;

    private final BlockingQueue<ProxyConnection> connections = new ArrayBlockingQueue<>(POOL_SIZE);

    private ConnectionPool() throws ConnectionPoolException {
        if (instance != null) {
            throw new RuntimeException("No more than one instance is allowed for ConnectionPool.");
        }
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnectionFactory proxyConnectionFactory = new ProxyConnectionFactory();
            ProxyConnection proxyConnection = proxyConnectionFactory.create();
            connections.offer(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {
        if (!isReady) {
            INSTANCE_LOCK.lock();
            try {
                if (!isReady) {
                    instance = new ConnectionPool();
                    isReady = true;
                }
            } catch (ConnectionPoolException e) {
                throw new RuntimeException("ConnectionPool instance is not created!", e);
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return instance;
    }


    public Connection getConnection() throws ConnectionPoolException {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
    }

    //package-private
    void releaseConnection(ProxyConnection proxyConnection) {
        connections.offer(proxyConnection);
    }

    public void destroy() throws ConnectionPoolException {
        closeConnections();
        deregisterDrivers();
    }

    private void closeConnections() throws ConnectionPoolException {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                ProxyConnection proxyConnection = connections.take();
                proxyConnection.closeConnection();
            }
        } catch (SQLException | InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
    }

    private void deregisterDrivers() throws ConnectionPoolException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        try {
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }
}
