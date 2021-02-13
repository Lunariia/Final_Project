package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.account.AccountService;
import com.epam.pharmacy.logic.purchase.PurchaseService;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CreatePrescriptionPageCommand implements Command {

    public static final String DATE_FORMAT_NOW = "dd-MM-yy";
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_NOW);
    private final PurchaseService purchaseService;
    private final AccountService accountService;

    public CreatePrescriptionPageCommand(PurchaseService purchaseService, AccountService accountService) {
        this.purchaseService = purchaseService;
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        String idParameter = req.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        HttpSession session = req.getSession();

        Purchase purchase = purchaseService.getById(id);
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = now.plusDays(7);

        String startDate = now.format(FORMAT);
        String endDate = then.format(FORMAT);;

        req.setAttribute(Attribute.MY_PRESCRIPTION_ACCOUNT, account);
        req.setAttribute(Attribute.MY_PRESCRIPTION_PURCHASE, purchase);
        req.setAttribute(Attribute.MY_PRESCRIPTION_START_DATE, startDate);
        req.setAttribute(Attribute.MY_PRESCRIPTION_END_DATE, endDate);

        return CommandResult.forward(Page.MY_PRESCRIPTION_PAGE);
    }
}
