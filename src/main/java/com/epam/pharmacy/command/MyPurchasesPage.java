package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.exception.NotFoundException;
import com.epam.pharmacy.logic.purchaseStory.PurchaseStoryService;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.PurchaseStory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyPurchasesPage implements Command {

    private final PurchaseStoryService purchaseStoryService;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_NUMBER_PAGE = 1;
    private static final int DEFAULT_ITEMS_PER_PAGE = 8;

    public MyPurchasesPage(PurchaseStoryService purchaseStoryService) {
        this.purchaseStoryService = purchaseStoryService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        String pageParameter = req.getParameter(Parameter.PAGE);
        int page = pageParameter == null
                ? DEFAULT_PAGE
                : Integer.parseInt(pageParameter);

        int numberOfPages = purchaseStoryService.getNumberOfPages(DEFAULT_ITEMS_PER_PAGE);
        if (page > numberOfPages) {
            throw new NotFoundException("Purchases pagination page not found: " + page);
        }

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);

        List<PurchaseStory> myPurchases = purchaseStoryService.getPageForOne(account.getId(), page, DEFAULT_ITEMS_PER_PAGE);

        req.setAttribute(Attribute.ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
        req.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);
        req.setAttribute(Attribute.PAGE, page);
        req.setAttribute(Attribute.MY_PURCHASES, myPurchases);

        return CommandResult.forward(Page.ACCOUNT_PURCHASES_PAGE);
    }
}
