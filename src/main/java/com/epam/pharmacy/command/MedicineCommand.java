package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.MedicineService;
import com.epam.pharmacy.logic.NotFoundException;
import com.epam.pharmacy.logic.ServiceException;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Medicine;
import com.epam.pharmacy.model.entity.Prescription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class MedicineCommand implements Command {

    private final MedicineService medicineService;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_ITEMS_PER_PAGE = 8;

    public MedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {

        String pageParameter = req.getParameter(Parameter.PAGE);
        int page = pageParameter == null
                ? DEFAULT_PAGE
                : Integer.parseInt(pageParameter);

        int numberOfPages = medicineService.getNumberOfPages(DEFAULT_ITEMS_PER_PAGE);
        if (page > numberOfPages) {
            throw new NotFoundException("Users pagination page not found: " + page);
        }

        List<Medicine> medicines = medicineService.getPage(page, DEFAULT_ITEMS_PER_PAGE);

        req.setAttribute(Attribute.ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
        req.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);
        req.setAttribute(Attribute.PAGE, page);
        req.setAttribute(Attribute.MEDICINES, medicines);

        return CommandResult.forward(Page.MEDICINES_PAGE);

    }
}
