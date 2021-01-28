package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.CommandName;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.MedicineService;
import com.epam.pharmacy.model.entity.Medicine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class SaveMedicineCommand implements Command{

    private static final String MEDICINE_COMMAND = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.MEDICINE;
    private final MedicineService medicineService;


    public SaveMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {

        String idParameter = req.getParameter(Parameter.ID);
        Long id = null;
        if (idParameter != null && !idParameter.isEmpty()) {
            id = Long.parseLong(idParameter);
        }

        String title = req.getParameter(Parameter.TITLE);

        String dosageParameter = req.getParameter(Parameter.DOSAGE);
        Double dosage = null;
        if (dosageParameter != null && !dosageParameter.isEmpty()) {
            dosage = Double.parseDouble(dosageParameter);
        }

        String costParameter = req.getParameter(Parameter.COST);
        Double cost = null;
        if (costParameter != null && !costParameter.isEmpty()) {
            cost = Double.parseDouble(costParameter);
        }

        String prescriptionParameter = req.getParameter(Parameter.PRESCRIPTION);
        Boolean prescription = null;
        if (prescriptionParameter != null && !prescriptionParameter.isEmpty()) {
            prescription = prescriptionParameter.equals("true");
        }

        String quantityParameter = req.getParameter(Parameter.QUANTITY);
        Long quantity = null;
        if (quantityParameter != null && !quantityParameter.isEmpty()) {
            quantity = Long.parseLong(quantityParameter);
        }


        Medicine medicine = new Medicine(id, title, dosage, cost, prescription, quantity);
        long confirmedId = medicineService.save(medicine);

        ServletContext servletContext = req.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + MEDICINE_COMMAND + "&" + Parameter.ID + "=" + confirmedId);
    }
}
