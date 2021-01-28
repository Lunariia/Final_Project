package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.CommandName;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.MedicineService;
import com.epam.pharmacy.model.entity.Medicine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class DeleteMedicineCommand implements Command {

    private static final String MEDICINE_COMMAND = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.MEDICINE;
    private final MedicineService medicineService;

    public DeleteMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        String idParameter = req.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        medicineService.deleteMedicineById(id);

        ServletContext servletContext = req.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + MEDICINE_COMMAND);
    }
}
