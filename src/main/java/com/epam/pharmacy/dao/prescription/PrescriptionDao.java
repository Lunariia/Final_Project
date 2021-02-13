package com.epam.pharmacy.dao.prescription;

import com.epam.pharmacy.dao.Dao;
import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.model.entity.Prescription;

import java.util.List;

public interface PrescriptionDao extends Dao<Prescription> {

    List<Prescription> findBatch(int amount, int from) throws DaoException;

    List<Prescription> findBatchForOne(long id, int amount, int from) throws DaoException;

    List<Prescription> findAllItems(long id,Boolean status) throws DaoException;

    long getPrescriptionAmount() throws DaoException;

    Long updateAccess(Prescription prescription) throws DaoException;

    Long addSingle(Long userId,Long medicineId,String startDate,String endDate) throws DaoException;

}
