package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.exception.NotFoundException;
import com.epam.pharmacy.logic.prescription.PrescriptionService;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Prescription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AccountPrescriptionPage implements Command {

    private final PrescriptionService prescriptionService;
    private static final int DEFAULT_PAGE = 1;
    private static final long DEFAULT_ID = 0L;
    private static final int DEFAULT_ITEMS_PER_PAGE = 8;

    public AccountPrescriptionPage(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {

        String pageParameter = req.getParameter(Parameter.PAGE);
        int page = pageParameter == null
                ? DEFAULT_PAGE
                : Integer.parseInt(pageParameter);

        int numberOfPages = prescriptionService.getNumberOfPages(DEFAULT_ITEMS_PER_PAGE);
        if (page > numberOfPages) {
            throw new NotFoundException("Users pagination page not found: " + page);
        }

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);

        List<Prescription> prescriptions = prescriptionService.getPageForOne(account.getId(), page, DEFAULT_ITEMS_PER_PAGE);

        req.setAttribute(Attribute.ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
        req.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);
        req.setAttribute(Attribute.PAGE, page);
        req.setAttribute(Attribute.PRESCRIPTIONS, prescriptions);

        return CommandResult.forward(Page.ACCOUNT_PRESCRIPTIONS_PAGE);
    }
}
