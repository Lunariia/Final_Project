package com.epam.pharmacy.logic.mapper;

import com.epam.pharmacy.model.entity.Purchase;
import com.epam.pharmacy.model.entity.PurchaseStory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PurchaseStoryMapper implements RowMapper<PurchaseStory> {

    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy";
    public static final SimpleDateFormat SIMPLE_FORMAT = new SimpleDateFormat(DATE_FORMAT_NOW);
    private static final String PURCHASE_STORY_ID_LABEL = "id";
    private static final String PURCHASE_STORY_USER_LABEL = "login";
    private static final String PURCHASE_STORY_MEDICINE_LABEL = "title";
    private static final String PURCHASE_STORY_AMOUNT_LABEL = "amount";
    private static final String PURCHASE_STORY_DATE_LABEL = "date_of_purchase";

    @Override
    public PurchaseStory map(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong(PURCHASE_STORY_ID_LABEL);
        Long amount = resultSet.getLong(PURCHASE_STORY_AMOUNT_LABEL);

        String user = resultSet.getString(PURCHASE_STORY_USER_LABEL);
        String medicine = resultSet.getString(PURCHASE_STORY_MEDICINE_LABEL);
        String resultDate = null;
        try {
            String date = resultSet.getString(PURCHASE_STORY_DATE_LABEL);
            Date dateFormat = new SimpleDateFormat("yy/MM/dd").parse(date);
            resultDate = SIMPLE_FORMAT.format(dateFormat);
        }catch(Exception e){
            System.out.println(e);
        }

        return new PurchaseStory(id, user, medicine, amount, resultDate);
    }
}
