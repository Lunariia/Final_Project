package com.epam.pharmacy.logic.validator;

import com.epam.pharmacy.model.entity.Medicine;

public class MedicineValidator implements Validator<Medicine> {


    private static final int MAX_TITLE_LENGTH = 100;
    private static final Long MIN_ID_VALUE = 1L;
    private static final Long MIN_AMOUNT_VALUE = 1L;
    private static final Long MAX_AMOUNT_VALUE = 50L;
    private static final Double MIN_COST_VALUE = 2.0;
    private static final Double MAX_COST_VALUE = 500.0;
    private static final Double MIN_DOSAGE_VALUE = 0.0;
    private static final Double MAX_DOSAGE_VALUE = 1.0;

    @Override
    public boolean isValid(Medicine medicine) {

        Long id = medicine.getId();
        String title = medicine.getTitle();
        Double dosage = medicine.getDosage();
        Double cost = medicine.getCost();
        Boolean prescription = medicine.getPrescription();
        Long quantity = medicine.getQuantity();

        if (title == null || title.isEmpty() || title.length() > MAX_TITLE_LENGTH) {
            return false;
        }
        if (id != null && id < MIN_ID_VALUE) {
            return false;
        }
        if (quantity != null && quantity < MIN_AMOUNT_VALUE && quantity > MAX_AMOUNT_VALUE) {
            return false;
        }
        if (cost != null && cost < MIN_COST_VALUE && cost > MAX_COST_VALUE) {
            return false;
        }
        if (dosage != null && dosage < MIN_DOSAGE_VALUE && dosage > MAX_DOSAGE_VALUE) {
            return false;
        }
        //--!--
        return prescription != null;
    }
}
