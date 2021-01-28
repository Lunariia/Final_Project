package com.epam.pharmacy.logic.mapper;

import com.epam.pharmacy.model.entity.Medicine;
import com.epam.pharmacy.model.entity.Purchase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseMapper implements RowMapper<Purchase> {

    private static final String PURCHASE_ID_LABEL = "id";
    private static final String PURCHASE_TITLE_LABEL = "title";
    private static final String PURCHASE_DOSAGE_LABEL = "dosage";
    private static final String PURCHASE_COST_LABEL = "cost";
    private static final String PURCHASE_FORMULA_LABEL = "formula";
    private static final String PURCHASE_QUANTITY_LABEL = "quantity";

    @Override
    public Purchase map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(PURCHASE_ID_LABEL);

        String title = resultSet.getString(PURCHASE_TITLE_LABEL);

        Long quantity = resultSet.getLong(PURCHASE_QUANTITY_LABEL);

        String tmpStr = resultSet.getString(PURCHASE_FORMULA_LABEL);
        Boolean formula = tmpStr.equals("1");

        String dosageStr = resultSet.getObject(PURCHASE_DOSAGE_LABEL).toString();
        Double dosage = Double.valueOf(dosageStr);

        String costStr = resultSet.getObject(PURCHASE_COST_LABEL).toString();
        Double cost = Double.valueOf(costStr);

        return new Purchase(id, title, dosage, cost, formula, quantity);
    }
}
