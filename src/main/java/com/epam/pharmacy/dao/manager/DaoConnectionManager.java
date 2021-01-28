package com.epam.pharmacy.dao.manager;

import com.epam.pharmacy.dao.*;
import com.epam.pharmacy.model.entity.Prescription;
import com.epam.pharmacy.model.entity.PurchaseStory;

public interface DaoConnectionManager extends AutoCloseable {

    UserDao createUserDao();

    MedicineDao createMedicineDao();

    PrescriptionDao createPrescriptionDao();

    PurchaseDao createPurchaseDao();

    PurchaseStoryDao createPurchaseStoryDao();

    void beginTransaction() throws DaoException;

    void commitTransaction() throws DaoException;
}
