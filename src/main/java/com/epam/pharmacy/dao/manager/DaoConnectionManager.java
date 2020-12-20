package com.epam.pharmacy.dao.manager;

import com.epam.pharmacy.dao.UserDao;

public interface DaoConnectionManager extends AutoCloseable {

    UserDao createAccountDao();

}
