package com.epam.pharmacy.dao;

import com.epam.pharmacy.logic.mapper.RowMapper;
import com.epam.pharmacy.model.entity.PurchaseStory;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class PurchaseStoryDaoImpl  extends AbstractDao<PurchaseStory> implements PurchaseStoryDao{

    private static final String SQL_SELECT_PAGE_FOR_SINGLE = "SELECT p.id, u.login, m.title,p.amount, p.date_of_purchase FROM purchases p join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id where u.id = ? ORDER BY id DESC LIMIT ? OFFSET ?;";

    private static final String SQL_SELECT_BY_ID = "SELECT p.id, u.login, m.title,p.amount, p.date_of_purchase FROM purchases p join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id where u.id = ?ж";

    private static final String SQL_SELECT_ALL = "SELECT p.id, u.login, m.title,p.amount, p.date_of_purchase FROM purchases p join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id;";

    private static final String SQL_SELECT_PAGE = "SELECT p.id, u.login, m.title,p.amount, p.date_of_purchase FROM purchases p join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id ORDER BY id DESC LIMIT ? OFFSET ?;";

    private static final String SQL_COUNT = "SELECT COUNT(*) FROM purchases";

    private static final Long NO_ITEMS_RESULT = 0L;

    public PurchaseStoryDaoImpl(Connection connection, RowMapper<PurchaseStory> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public List<PurchaseStory> findBatch(int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE, amount, offset);
    }

    @Override
    public List<PurchaseStory> findBatchForOne(long id, int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE_FOR_SINGLE,id, amount, offset);
    }

    @Override
    public long getPurchaseStoryAmount() throws DaoException {
        Optional<Object> result = selectScalar(SQL_COUNT);
        return (long) result.orElse(NO_ITEMS_RESULT);
    }

    @Override
    public List<PurchaseStory> getAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<PurchaseStory> getById(long id) throws DaoException {
        return selectSingle(SQL_SELECT_BY_ID, id);
    }

    @Override
    public Long add(PurchaseStory element) throws DaoException {
        return null;
    }

    @Override
    public void delete(long id) throws DaoException {

    }

}
