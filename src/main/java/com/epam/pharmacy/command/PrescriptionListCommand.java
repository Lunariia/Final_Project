package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.NotFoundException;
import com.epam.pharmacy.logic.PrescriptionService;
import com.epam.pharmacy.model.entity.Prescription;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrescriptionListCommand implements Command {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_ITEMS_PER_PAGE = 8;

    private final PrescriptionService prescriptionService;

    public PrescriptionListCommand(PrescriptionService prescriptionService) {
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

        List<Prescription> prescriptions = prescriptionService.getPage(page, DEFAULT_ITEMS_PER_PAGE);

        req.setAttribute(Attribute.ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
        req.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);
        req.setAttribute(Attribute.PAGE, page);
        req.setAttribute(Attribute.PRESCRIPTIONS, prescriptions);

        return CommandResult.forward(Page.PRESCRIPTIONS_PAGE);
    }
}
