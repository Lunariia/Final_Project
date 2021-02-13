package com.epam.pharmacy.dao.purchase;

import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.logic.mapper.RowMapper;

import com.epam.pharmacy.model.entity.Purchase;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class PurchaseDaoImpl extends AbstractDao<Purchase> implements PurchaseDao{

    private static final String SQL_SELECT_PAGE = "SELECT id, title, dosage, cost, formula, quantity " +
            "FROM medicines ORDER BY cost DESC LIMIT ? OFFSET ?;";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM medicines";
    private static final String SQL_SELECT_ALL = "SELECT id, title, dosage, cost, formula, quantity FROM medicines;";
    private static final Long NO_ITEMS_RESULT = 0L;
    private static final String SQL_DELETE_BY_ID = "DELETE FROM medicines WHERE id = ?;";
    private static final String SQL_SELECT_BY_ID = "SELECT id, title, dosage, cost, formula, quantity " +
            "FROM medicines WHERE id = ?;";
    private static final String SQL_INSERT_PURCHASE = "INSERT INTO medicines (title, dosage, cost, formula, quantity) " +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_INSERT_PURCHASE_ITEM = "insert into purchases(user_id,medicine_id,amount,date_of_purchase) " +
            "VALUES (?, ?, ?,DATE_FORMAT(?,'%d/%m/%y'));";
    private static final String SQL_UPDATE_PURCHASE = "UPDATE medicines " +
            "SET title = ?, dosage = ?, cost = ?, formula = ?, quantity = ? " +
            "WHERE id = ?;";

    public PurchaseDaoImpl(Connection connection, RowMapper<Purchase> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public List<Purchase> findBatch(int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE, amount, offset);
    }

    @Override
    public long getPurchaseAmount() throws DaoException {
        Optional<Object> result = selectScalar(SQL_COUNT);
        return (long) result.orElse(NO_ITEMS_RESULT);
    }

    @Override
    public Long addItem(Long userId, Long medicineId, Long quantity, String purchaseDate) throws DaoException {
        Optional<Long> result = updateSingle(SQL_INSERT_PURCHASE_ITEM, userId, medicineId, quantity, purchaseDate);
        return result.orElseThrow(() -> new DaoException("Unacceptable query result!"));
    }

    @Override
    public List<Purchase> getAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<Purchase> getById(long id) throws DaoException {
        return selectSingle(SQL_SELECT_BY_ID, id);
    }

    @Override
    public Long add(Purchase purchase) throws DaoException {
        Long id = purchase.getId();
        String title = purchase.getTitle();
        Double dosage = purchase.getDosage();
        Double cost = purchase.getCost();
        Boolean formula = purchase.getPrescription();
        Long quantity = purchase.getQuantity();
        if (id == null) {
            Optional<Long> result = updateSingle(SQL_INSERT_PURCHASE, title, dosage, cost, formula, quantity);
            return result.orElseThrow(() -> new DaoException("Unacceptable query result!"));
        } else {
            updateSingle(SQL_UPDATE_PURCHASE, title, dosage, cost, formula, quantity, id);
            return id;
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        updateSingle(SQL_DELETE_BY_ID, id);
    }
}
