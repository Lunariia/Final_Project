package com.epam.pharmacy.logic.validator;

import com.epam.pharmacy.model.entity.Prescription;

public class PrescriptionValidator implements Validator<Prescription> {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final Long MIN_ID_VALUE = 1L;

    @Override
    public boolean isValid(Prescription prescription) {

        Long id = prescription.getId();
        String user = prescription.getUser();
        String medicine = prescription.getMedicine();
        String start_date = prescription.getStartDate();
        String end_date = prescription.getEndDate();
        Boolean access = prescription.getAccess();

        if (user == null || user.isEmpty() || user.length() > MAX_TITLE_LENGTH) {
            return false;
        }

        if (id != null && id < MIN_ID_VALUE) {
            return false;
        }

        if (medicine == null || medicine.isEmpty() || medicine.length() > MAX_TITLE_LENGTH) {
            return false;
        }

        return access != null;
    }
}
