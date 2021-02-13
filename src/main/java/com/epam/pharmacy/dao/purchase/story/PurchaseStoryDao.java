package com.epam.pharmacy.dao.purchase.story;

import com.epam.pharmacy.dao.Dao;
import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.model.entity.PurchaseStory;

import java.util.List;

public interface PurchaseStoryDao  extends Dao<PurchaseStory> {

    List<PurchaseStory> findBatch(int amount, int from) throws DaoException;

    List<PurchaseStory> findBatchForOne(long id, int amount, int from) throws DaoException;

    long getPurchaseStoryAmount() throws DaoException;

}
