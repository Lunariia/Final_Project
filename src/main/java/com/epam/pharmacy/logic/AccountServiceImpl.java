package com.epam.pharmacy.logic;

import com.epam.pharmacy.dao.DaoException;
import com.epam.pharmacy.dao.PrescriptionDao;
import com.epam.pharmacy.dao.UserDao;
import com.epam.pharmacy.dao.manager.DaoConnectionManager;
import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.model.entity.Account;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private static final int MAX_LOGIN_LENGTH = 30;
    private final DaoConnectionManagerFactory factory;


    public AccountServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Account> authenticate(String login, String password) throws ServiceException {
        if (login == null || login.isEmpty() || login.length() > MAX_LOGIN_LENGTH) {
            throw new ServiceException("Invalid username value: " + login);
        }
        if (password == null || password.isEmpty()) {
            throw new ServiceException("Invalid password value: " + password);
        }
        try (DaoConnectionManager manager = factory.create()) {
            UserDao accountDao = manager.createUserDao();
            return accountDao.findByCredentials(login, password);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long updateBalance(Double balance,Long id) throws Exception {

        try (DaoConnectionManager manager = factory.create()) {
            UserDao accountDao = manager.createUserDao();
            return accountDao.updateBalance(balance,id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
