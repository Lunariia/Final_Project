package com.epam.pharmacy.dao.manager;

import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.dao.medicine.MedicineDao;
import com.epam.pharmacy.dao.medicine.MedicineDaoImpl;
import com.epam.pharmacy.dao.prescription.PrescriptionDao;
import com.epam.pharmacy.dao.prescription.PrescriptionDaoImpl;
import com.epam.pharmacy.dao.purchase.PurchaseDao;
import com.epam.pharmacy.dao.purchase.PurchaseDaoImpl;
import com.epam.pharmacy.dao.purchase.story.PurchaseStoryDao;
import com.epam.pharmacy.dao.purchase.story.PurchaseStoryDaoImpl;
import com.epam.pharmacy.dao.user.UserDao;
import com.epam.pharmacy.dao.user.UserDaoImpl;
import com.epam.pharmacy.logic.mapper.*;
import com.epam.pharmacy.model.entity.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoConnectionManagerImpl implements DaoConnectionManager {

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
    public UserDao createUserDao() {
        RowMapper<Account> rowMapper = new AccountMapper();
        return new UserDaoImpl(connection, rowMapper);
    }

    @Override
    public MedicineDao createMedicineDao() {
        RowMapper<Medicine> rowMapper = new MedicineMapper();
        return new MedicineDaoImpl(connection, rowMapper);
    }

    @Override
    public PrescriptionDao createPrescriptionDao() {
        RowMapper<Prescription> rowMapper = new PrescriptionMapper();
        return new PrescriptionDaoImpl(connection, rowMapper);
    }

    @Override
    public PurchaseDao createPurchaseDao() {
        RowMapper<Purchase> rowMapper = new PurchaseMapper();
        return new PurchaseDaoImpl(connection, rowMapper);
}

    @Override
    public PurchaseStoryDao createPurchaseStoryDao() {
        RowMapper<PurchaseStory> rowMapper = new PurchaseStoryMapper();
        return new PurchaseStoryDaoImpl(connection, rowMapper);
    }

    @Override
    public void beginTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void commitTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException commitException) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                throw new DaoException(rollbackException);
            }
        }
    }

}
