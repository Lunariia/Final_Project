package com.epam.pharmacy.logic.mapper;

import com.epam.pharmacy.model.entity.Medicine;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineMapper implements RowMapper<Medicine> {

    private static final String MEDICINE_ID_LABEL = "id";
    private static final String MEDICINE_TITLE_LABEL = "title";
    private static final String MEDICINE_DOSAGE_LABEL = "dosage";
    private static final String MEDICINE_COST_LABEL = "cost";
    private static final String MEDICINE_FORMULA_LABEL = "formula";
    private static final String MEDICINE_QUANTITY_LABEL = "quantity";

    @Override
    public Medicine map(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong(MEDICINE_ID_LABEL);
        String title = resultSet.getString(MEDICINE_TITLE_LABEL);
        Long quantity = resultSet.getLong(MEDICINE_QUANTITY_LABEL);

        String tmpStr = resultSet.getString(MEDICINE_FORMULA_LABEL);
        Boolean formula = tmpStr.equals("1");

        String dosageStr = resultSet.getObject(MEDICINE_DOSAGE_LABEL).toString();
        Double dosage = Double.valueOf(dosageStr);

        String costStr = resultSet.getObject(MEDICINE_COST_LABEL).toString();
        Double cost = Double.valueOf(costStr);

        return new Medicine(id, title, dosage, cost, formula, quantity);
    }
}
