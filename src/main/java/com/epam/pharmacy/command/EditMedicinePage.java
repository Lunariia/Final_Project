package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.MedicineService;
import com.epam.pharmacy.model.entity.Medicine;

import javax.servlet.http.HttpServletRequest;

public class EditMedicinePage implements Command {

    private final MedicineService medicineService;

    public EditMedicinePage(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        String idParameter = req.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        Medicine medicine = medicineService.getById(id);
        req.setAttribute(Attribute.EDIT_MEDICINE, medicine);

        return CommandResult.forward(Page.MEDICINE_EDITOR);
    }
}
