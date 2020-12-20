package com.epam.pharmacy.login;

import com.epam.pharmacy.dao.UserDao;
import com.epam.pharmacy.dao.manager.DaoConnectionManager;
import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.logic.ServiceException;
import com.epam.pharmacy.model.entity.Account;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private final DaoConnectionManagerFactory factory;

    public AccountServiceImpl(DaoConnectionManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Account> authenticate(String login, String password) throws ServiceException {
        try (DaoConnectionManager manager = factory.create()) {
            UserDao accountDao = manager.createAccountDao();
            return accountDao.findByCredentials(login, password);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
