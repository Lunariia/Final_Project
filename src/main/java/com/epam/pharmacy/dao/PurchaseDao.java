package com.epam.pharmacy.dao;


import com.epam.pharmacy.model.entity.Medicine;
import com.epam.pharmacy.model.entity.Prescription;
import com.epam.pharmacy.model.entity.Purchase;

import java.util.List;

public interface PurchaseDao extends Dao<Purchase> {

    List<Purchase> findBatch(int amount, int from) throws DaoException;

    long getPurchaseAmount() throws DaoException;

    Long addItem(Long userId,Long medicineId,Long quantity,String purchaseDate) throws DaoException;
}
