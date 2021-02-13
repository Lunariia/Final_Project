package com.epam.pharmacy.dao.prescription;

import com.epam.pharmacy.dao.AbstractDao;
import com.epam.pharmacy.dao.exception.DaoException;
import com.epam.pharmacy.logic.mapper.RowMapper;
import com.epam.pharmacy.model.entity.Prescription;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class PrescriptionDaoImpl extends AbstractDao<Prescription> implements PrescriptionDao {

    private static final String SQL_SELECT_PAGE = "SELECT p.id, u.login, m.title, p.start_date,p.end_date, p.access " +
            "FROM prescriptions p join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id ORDER BY id DESC LIMIT ? OFFSET ?;";

    private static final String SQL_COUNT = "SELECT COUNT(*) FROM prescriptions";

    private static final String SQL_ALL_ITEMS = "SELECT p.id, u.login, m.title, p.start_date,p.end_date, p.access FROM prescriptions p join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id WHERE u.id = ? and p.access = ?;";

    private static final String SQL_SELECT_ALL = "SELECT p.id, u.login, m.title, p.start_date,p.end_date, p.access FROM prescriptions p \n" +
            "join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id";
    private static final Long NO_ITEMS_RESULT = 0L;

    private static final String SQL_DELETE_BY_ID = "DELETE FROM prescriptions WHERE id = ?;";

    private static final String SQL_SELECT_BY_ID = "SELECT p.id, u.login, m.title, p.start_date,p.end_date, p.access" +
            " FROM prescriptions p join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id WHERE p.id = ?;";
    private static final String SQL_INSERT_PRESCRIPTION = "INSERT INTO prescriptions (id, user_id, medicine_id, start_date, end_date, access) " +
            "VALUES (?, ?, ?, ?, ?, null);";
    private static final String SQL_UPDATE_PRESCRIPTION_ACCESS = "UPDATE prescriptions SET access = ? WHERE id = ?;";

    private static final String SQL_SELECT_PAGE_FOR_SINGLE = "SELECT p.id, u.login, m.title, p.start_date,p.end_date, p.access " +
            "FROM prescriptions p join medicines m on m.id = p.medicine_id join users u on u.id = p.user_id where u.id = ? ORDER BY id DESC LIMIT ? OFFSET ?;";

    private static final String SQL_INSERT_PRESCRIPTION_SINGLE = "insert into Prescriptions(user_id,medicine_id,start_date,end_date)\n" +
            "values(?,?,DATE_FORMAT(?,'%d/%m/%y'),DATE_FORMAT(?,'%d/%m/%y'));";


    public PrescriptionDaoImpl(Connection connection, RowMapper<Prescription> rowMapper) {
        super(connection, rowMapper);
    }

    @Override
    public List<Prescription> findBatch(int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE, amount, offset);
    }

    @Override
    public List<Prescription> findBatchForOne(long id, int amount, int from) throws DaoException {
        int offset = from - 1;
        return selectSeveral(SQL_SELECT_PAGE_FOR_SINGLE,id, amount, offset);
    }

    @Override
    public List<Prescription> findAllItems(long id,Boolean status) throws DaoException {
        return selectSeveral(SQL_ALL_ITEMS,id,status);
    }

    @Override
    public long getPrescriptionAmount() throws DaoException {
        Optional<Object> result = selectScalar(SQL_COUNT);
        return (long) result.orElse(NO_ITEMS_RESULT);
    }

    @Override
    public List<Prescription> getAll() throws DaoException {
        return selectSeveral(SQL_SELECT_ALL);
    }

    @Override
    public Optional<Prescription> getById(long id) throws DaoException {
        return selectSingle(SQL_SELECT_BY_ID, id);
    }

    @Override
    public Long updateAccess(Prescription prescription) throws DaoException {
        Long id = prescription.getId();
        String user_id = prescription.getUser();
        String medicine_id = prescription.getMedicine();
        String start_date = prescription.getStartDate();
        String end_date = prescription.getEndDate();
        Boolean access = prescription.getAccess();

        if (id == null) {
            Optional<Long> result = updateSingle(SQL_INSERT_PRESCRIPTION, id, user_id, medicine_id, start_date, end_date);
            return result.orElseThrow(() -> new DaoException("Unacceptable query result!"));
        } else {
            updateSingle(SQL_UPDATE_PRESCRIPTION_ACCESS, access,id);
            return id;
        }
    }

    @Override
    public Long add(Prescription prescription) throws DaoException {
        return null;
    }

    @Override
    public Long addSingle(Long userId, Long medicineId, String startDate, String endDate) throws DaoException {

            Optional<Long> result = updateSingle(SQL_INSERT_PRESCRIPTION_SINGLE, userId, medicineId, startDate, endDate);
            return result.orElseThrow(() -> new DaoException("Unacceptable query result!"));
    }

    @Override
    public void delete(long id) throws DaoException {
        updateSingle(SQL_DELETE_BY_ID, id);
    }

}
