package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.CommandName;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.PrescriptionService;
import com.epam.pharmacy.model.entity.Prescription;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class RefusePrescription implements Command {

    private static final String PRESCRIPTION_COMMAND = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.PRESCRIPTIONS_PAGE;
    private final PrescriptionService prescriptionService;

    public RefusePrescription(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        String idParameter = req.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        Prescription p = prescriptionService.getById(id);
        Prescription prescriptionEdit = new Prescription(p.getId(),p.getUser(),p.getMedicine(),p.getStartDate(),p.getEndDate(),false);
        long confirmedId = prescriptionService.updateAccess(prescriptionEdit);

        ServletContext servletContext = req.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + PRESCRIPTION_COMMAND + "&" + Parameter.ID + "=" + confirmedId);
    }
}
