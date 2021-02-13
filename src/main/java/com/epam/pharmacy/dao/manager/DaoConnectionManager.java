package com.epam.pharmacy.dao.manager;

import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.dao.medicine.MedicineDao;
import com.epam.pharmacy.dao.prescription.PrescriptionDao;
import com.epam.pharmacy.dao.purchase.PurchaseDao;
import com.epam.pharmacy.dao.purchase.story.PurchaseStoryDao;
import com.epam.pharmacy.dao.user.UserDao;

public interface DaoConnectionManager extends AutoCloseable {

    UserDao createUserDao();

    MedicineDao createMedicineDao();

    PrescriptionDao createPrescriptionDao();

    PurchaseDao createPurchaseDao();

    PurchaseStoryDao createPurchaseStoryDao();

    void beginTransaction() throws DaoException;

    void commitTransaction() throws DaoException;
}
