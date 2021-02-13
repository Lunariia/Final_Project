package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.CommandName;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.account.AccountService;
import com.epam.pharmacy.logic.prescription.PrescriptionService;
import com.epam.pharmacy.logic.purchase.PurchaseService;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Prescription;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderCommand implements Command {

    private static final String DATE_FORMAT_NOW = "dd-MM-yy";
    private static final String ERROR_MASSAGE_PRESCRIPTION = "You have no prescription for this medicine!";
    private static final String ERROR_MASSAGE_MONEY = "You have no enough money!";
    private static final Long QUANTITY = 1L;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_NOW);
    private static final String PURCHASE_COMMAND = "/controller" + "?" + Parameter.COMMAND + "=" + CommandName.PURCHASES_PAGE;
    private final PurchaseService purchaseService;
    private final AccountService accountService;
    private final PrescriptionService prescriptionService;

    public OrderCommand(PurchaseService purchaseService, AccountService accountService,PrescriptionService prescriptionService) {
        this.purchaseService = purchaseService;
        this.prescriptionService = prescriptionService;
        this.accountService = accountService;
    }


    //уточнить какой exception!
    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        String purchaseDate = now.format(FORMAT);

        ServletContext servletContext = req.getServletContext();
        String contextPath = servletContext.getContextPath();

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



        Boolean search = false;
        if (prescription.equals("true")){
            List<Prescription> prescriptions = prescriptionService.getAllItems(account.getId(),true);
            for (Prescription item:prescriptions) {
                if (item.getMedicine().equals(title)){
                    search = true;
                }
            }
        }else{
            search = true;
        }

        //check
        if (search.equals(false)) {
                req.setAttribute(Attribute.MESSAGE, ERROR_MASSAGE_PRESCRIPTION);
                return CommandResult.forward(Page.MESSAGE_PAGE);
        }

        Double checkPurchase = balance - cost;
        if (checkPurchase < 0) {
            req.setAttribute(Attribute.MESSAGE, ERROR_MASSAGE_MONEY);
            return CommandResult.forward(Page.MESSAGE_PAGE);
        }else{
            long confirmedId = purchaseService.saveItem(userId, medicineId, QUANTITY, purchaseDate);
            accountService.updateBalance(checkPurchase,userId);
        }

        return CommandResult.redirect(contextPath + PURCHASE_COMMAND);
    }
}
