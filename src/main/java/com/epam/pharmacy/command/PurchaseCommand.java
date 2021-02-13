package com.epam.pharmacy.command;


import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.exception.NotFoundException;
import com.epam.pharmacy.logic.purchase.PurchaseService;
import com.epam.pharmacy.model.entity.Purchase;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PurchaseCommand implements Command {

    private final PurchaseService purchaseService;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_ITEMS_PER_PAGE = 8;

    public PurchaseCommand(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        String pageParameter = req.getParameter(Parameter.PAGE);
        int page = pageParameter == null
                ? DEFAULT_PAGE
                : Integer.parseInt(pageParameter);

        int numberOfPages = purchaseService.getNumberOfPages(DEFAULT_ITEMS_PER_PAGE);
        if (page > numberOfPages) {
            throw new NotFoundException("Users pagination page not found: " + page);
        }

        List<Purchase> purchases = purchaseService.getPage(page, DEFAULT_ITEMS_PER_PAGE);

        req.setAttribute(Attribute.ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
        req.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);
        req.setAttribute(Attribute.PAGE, page);
        req.setAttribute(Attribute.PURCHASES, purchases);

        return CommandResult.forward(Page.PURCHASES_PAGE);
    }
}
