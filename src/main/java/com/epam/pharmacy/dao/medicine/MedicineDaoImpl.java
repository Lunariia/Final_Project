package com.epam.pharmacy.dao.medicine;

import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.logic.mapper.RowMapper;
import com.epam.pharmacy.model.entity.Medicine;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class MedicineDaoImpl extends AbstractDao<Medicine> implements MedicineDao {

    private static final String SQL_SELECT_PAGE = "SELECT id, title, dosage, cost, formula, quantity " +
            "FROM medicines ORDER BY cost DESC LIMIT ? OFFSET ?;";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM medicines";
    ;
    private static final String SQL_SELECT_ALL = "SELECT id, title, dosage, cost, formula, quantity FROM medicines;";
    private static final Long NO_ITEMS_RESULT = 0L;
    private static final String SQL_DELETE_BY_ID = "DELETE FROM medicines WHERE id = ?;";
    private static final String SQL_SELECT_BY_ID = "SELECT id, title, dosage, cost, formula, quantity " +
            "FROM medicines WHERE id = ?;";
    private static final String SQL_INSERT_MEDICINE = "INSERT INTO medicines (title, dosage, cost, formula, quantity) " +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE_MEDICINE = "UPDATE medicines " +
            "SET title = ?, dosage = ?, cost = ?, formula = ?, quantity = ? " +
            "WHERE id = ?;";

    public MedicineDaoImpl(Connection connection, RowMapper<Medicine> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public List<Medicine> findBatch(int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE, amount, offset);
    }

    @Override
    public long getMedicinesAmount() throws DaoException {
        Optional<Object> result = selectScalar(SQL_COUNT);
        return (long) result.orElse(NO_ITEMS_RESULT);
    }

    @Override
    public List<Medicine> getAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<Medicine> getById(long id) throws DaoException {
        return selectSingle(SQL_SELECT_BY_ID, id);
    }

    @Override
    public Long add(Medicine medicine) throws DaoException {
        Long id = medicine.getId();
        String title = medicine.getTitle();
        Double dosage = medicine.getDosage();
        Double cost = medicine.getCost();
        Boolean formula = medicine.getPrescription();
        Long quantity = medicine.getQuantity();
        if (id == null) {
            Optional<Long> result = updateSingle(SQL_INSERT_MEDICINE, title, dosage, cost, formula, quantity);
            return result.orElseThrow(() -> new DaoException("Unacceptable query result!"));
        } else {
            updateSingle(SQL_UPDATE_MEDICINE, title, dosage, cost, formula, quantity, id);
            return id;
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        updateSingle(SQL_DELETE_BY_ID, id);
    }


}
