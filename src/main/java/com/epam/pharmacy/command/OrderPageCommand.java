package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.PurchaseService;
import com.epam.pharmacy.model.entity.Purchase;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderPageCommand implements Command {

    public static final String DATE_FORMAT_NOW = "dd-MM-yy";
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_NOW);
    private final PurchaseService purchaseService;

    public OrderPageCommand(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws Exception {
        String idParameter = req.getParameter(Parameter.ID);
        long id = Long.parseLong(idParameter);

        LocalDateTime now = LocalDateTime.now();
        String purchaseDate = now.format(FORMAT);

        Purchase order = purchaseService.getById(id);
        req.setAttribute(Attribute.ORDER, order);
        req.setAttribute(Attribute.PURCHASE_DATE, purchaseDate);

        return CommandResult.forward(Page.ORDER_PAGE);
    }
}
