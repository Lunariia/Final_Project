package com.epam.pharmacy.logic.mapper;

import com.epam.pharmacy.model.entity.Prescription;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionMapper implements RowMapper<Prescription> {

    private static final String PRESCRIPTION_ID_LABEL = "id";
    private static final String PRESCRIPTION_USER_LABEL = "login";
    private static final String PRESCRIPTION_MEDICINE_LABEL = "title";
    private static final String PRESCRIPTION_START_DATE_LABEL = "start_date";
    private static final String PRESCRIPTION_END_DATE_LABEL = "end_date";
    private static final String PRESCRIPTION_ACCESS_LABEL = "access";


    @Override
    public Prescription map(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(PRESCRIPTION_ID_LABEL);
        String user = resultSet.getString(PRESCRIPTION_USER_LABEL);
        String medicine = resultSet.getString(PRESCRIPTION_MEDICINE_LABEL);
        String startDate = resultSet.getString(PRESCRIPTION_START_DATE_LABEL);
        String endDate = resultSet.getString(PRESCRIPTION_END_DATE_LABEL);
        Boolean access;

        String tmpBool = resultSet.getString(PRESCRIPTION_ACCESS_LABEL);
        if (tmpBool == null) {
             access = null;
        } else {
             access = tmpBool.equals("1");
        }
        return new Prescription(id, user, medicine, startDate, endDate, access);
    }
}
