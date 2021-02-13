package com.epam.pharmacy.dao.purchase;


import com.epam.pharmacy.dao.Dao;
import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.model.entity.Purchase;

import java.util.List;

public interface PurchaseDao extends Dao<Purchase> {

    List<Purchase> findBatch(int amount, int from) throws DaoException;

    long getPurchaseAmount() throws DaoException;

    Long addItem(Long userId,Long medicineId,Long quantity,String purchaseDate) throws DaoException;
}
