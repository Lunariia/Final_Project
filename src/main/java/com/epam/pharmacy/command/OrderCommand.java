package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.CommandName;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.AccountService;
import com.epam.pharmacy.logic.PrescriptionService;
import com.epam.pharmacy.logic.PurchaseService;
import com.epam.pharmacy.logic.ServiceException;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Prescription;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderCommand implements Command {

    public static final String DATE_FORMAT_NOW = "dd-MM-yy";
    public static final Long QUANTITY = 1L;
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_NOW);
    private static final String PURCHASE_COMMAND = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.PURCHASES_PAGE;
    private final PurchaseService purchaseService;
    private final AccountService accountService;
    private final PrescriptionService prescriptionService;

    public OrderCommand(PurchaseService purchaseService, AccountService accountService,PrescriptionService prescriptionService) {
        this.purchaseService = purchaseService;
        this.prescriptionService = prescriptionService;
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String purchaseDate = now.format(FORMAT);

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);

        Long balance = account.getBalance();
        Long userId = account.getId();
        String prescription = req.getParameter(Parameter.PRESCRIPTION);
        String title = req.getParameter(Parameter.TITLE);

        String idParameter = req.getParameter(Parameter.ID);
        Long medicineId = null;
        if (idParameter != null && !idParameter.isEmpty()) {
            medicineId = Long.parseLong(idParameter);
        }

        String costParameter = req.getParameter(Parameter.COST);
        Double cost = null;
        if (costParameter != null && !costParameter.isEmpty()) {
            cost = Double.parseDouble(costParameter);
        }

        List<Prescription> prescriptions = prescriptionService.getAllItems(account.getId(),true);

        Boolean search = false;
        if (prescription.equals("true")){
            for (Prescription item:prescriptions) {
                if (item.getMedicine().equals(title)){
                    search = true;
                }
            }
        }

        //check
        if (search.equals(false)) {
            throw new ServiceException("You need Prescription");
        }

        Double checkPurchase = balance - cost;
        if (checkPurchase < 0) {
            throw new ServiceException("No enough money!");
        }else{
            long confirmedId = purchaseService.saveItem(userId, medicineId, QUANTITY, purchaseDate);
            long changedId = accountService.updateBalance(checkPurchase,userId);
        }

        ServletContext servletContext = req.getServletContext();
        String contextPath = servletContext.getContextPath();
        return CommandResult.redirect(contextPath + PURCHASE_COMMAND );
    }
}
