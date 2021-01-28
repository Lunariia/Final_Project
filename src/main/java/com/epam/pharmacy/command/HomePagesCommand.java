package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;

import javax.servlet.http.HttpServletRequest;

public class HomePagesCommand implements Command {

    private static final String ERROR_MASSAGE = "Invalid data";
    private String command;

    public HomePagesCommand(String command) {
        this.command = command;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) {
        switch (command) {

            case Attribute.CONTACTS_ATTRIBUTE:
                return CommandResult.forward(Page.CONTACTS_PAGE);

            case Attribute.ABOUT_US_ATTRIBUTE:
                return CommandResult.forward(Page.ABOUT_US_PAGE);

            case Attribute.SIGN_IN_ATTRIBUTE:
                return CommandResult.forward(Page.SIGN_IN_PAGE);

            case Attribute.DEPARTMENTS_ATTRIBUTE:
                return CommandResult.forward(Page.DEPARTMENTS_PAGE);

            case Attribute.HOME_ATTRIBUTE:
                return CommandResult.forward(Page.HOME);

            default:
                req.setAttribute(Attribute.ERROR_ATTRIBUTE, ERROR_MASSAGE);
                return CommandResult.forward(Page.INDEX);
        }
    }
}
