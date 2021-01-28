package com.epam.pharmacy.dao;

import com.epam.pharmacy.model.entity.Medicine;

import java.util.List;

public interface MedicineDao extends Dao<Medicine>{

    List<Medicine> findBatch(int amount, int from) throws DaoException;

    long getMedicinesAmount() throws DaoException;

}
