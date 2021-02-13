package com.epam.pharmacy.logic.validator;

import com.epam.pharmacy.model.entity.PurchaseStory;

public class PurchaseStoryValidator implements Validator<PurchaseStory> {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final Long MIN_ID_VALUE = 1L;
    private static final Long MIN_AMOUNT_VALUE = 1L;
    private static final Long MAX_AMOUNT_VALUE = 50L;
    private static final Double MIN_COST_VALUE = 2.0;
    private static final Double MAX_COST_VALUE = 500.0;
    private static final Double MIN_DOSAGE_VALUE = 0.0;
    private static final Double MAX_DOSAGE_VALUE = 1.0;

    @Override
    public boolean isValid(PurchaseStory purchaseStory) {
        Long id = purchaseStory.getId();
        String user = purchaseStory.getUser();
        String medicine = purchaseStory.getMedicine();
        Long amount = purchaseStory.getAmount();
        String date = purchaseStory.getDateOfPurchase();

        if (user == null || user.isEmpty() || user.length() > MAX_TITLE_LENGTH) {
            return false;
        }
        if (medicine == null || medicine.isEmpty() || medicine.length() > MAX_TITLE_LENGTH) {
            return false;
        }
        if (id != null && id < MIN_ID_VALUE) {
            return false;
        }
        if (amount != null && amount < MIN_AMOUNT_VALUE && amount > MAX_AMOUNT_VALUE) {
            return false;
        }

        //--!--
        return date != null;
    }
}
