package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.CommandName;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.AccountService;
import com.epam.pharmacy.logic.PrescriptionService;
import com.epam.pharmacy.logic.PurchaseService;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Medicine;
import com.epam.pharmacy.model.entity.Purchase;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;

public class CreatePrescriptionCommand implements Command {

    private static final String MY_PRESCRIPTION_COMMAND = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.PURCHASES_PAGE;
    public static final String DATE_FORMAT_NOW = "dd-MM-yy";
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_NOW);
    private final PurchaseService purchaseService;
    private final AccountService accountService;
    private final PrescriptionService prescriptionService;

    public CreatePrescriptionCommand(PurchaseService purchaseService, AccountService accountService, PrescriptionService prescriptionService) {
        this.purchaseService = purchaseService;
        this.accountService = accountService;
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {

        String idParameter = req.getParameter(Parameter.MEDICINE_ID);
        Long medicineId = null;
        if (idParameter != null && !idParameter.isEmpty()) {
            medicineId = Long.parseLong(idParameter);
        }

        String idUserParameter = req.getParameter(Parameter.USER_ID);
        Long userId = null;
        if (idUserParameter != null && !idUserParameter.isEmpty()) {
            userId = Long.parseLong(idUserParameter);
        }

        String startDate = req.getParameter(Parameter.START_DATE);
        String endDate = req.getParameter(Parameter.END_DATE);

        long confirmedId = prescriptionService.save(userId,medicineId,startDate,endDate);

        ServletContext servletContext = req.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + MY_PRESCRIPTION_COMMAND);
    }
}
