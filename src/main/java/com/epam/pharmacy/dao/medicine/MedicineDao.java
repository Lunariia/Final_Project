package com.epam.pharmacy.dao.medicine;

import com.epam.pharmacy.dao.Dao;
import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.model.entity.Medicine;

import java.util.List;

public interface MedicineDao extends Dao<Medicine> {

    List<Medicine> findBatch(int amount, int from) throws DaoException;

    long getMedicinesAmount() throws DaoException;

}
