package com.epam.pharmacy.pages;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageCommand implements Command {

    private String command;

    public PageCommand(String command) {
        this.command = command;
    }

    private static final String CONTACTS_ATTRIBUTE = "contacts";
    private static final String SIGN_IN_ATTRIBUTE = "signIn";
    private static final String ABOUT_US_ATTRIBUTE = "aboutUs";
    private static final String DEPARTMENTS_ATTRIBUTE = "departments";
    private static final String CONTACTS_PAGE = "WEB-INF/view/homeContacts.jsp";
    private static final String SIGN_IN_PAGE = "WEB-INF/view/login.jsp";
    private static final String ABOUT_US_PAGE = "WEB-INF/view/homeAbout.jsp";
    private static final String DEPARTMENTS_PAGE = "WEB-INF/view/homePharmacies.jsp";
    private static final String ERROR_ATTRIBUTE = "errorMessage";
    private static final String ERROR_MASSAGE = "Invalid data";
    private static final String HOME_PAGE = "WEB-INF/view/index.jsp";


    @Override
    public CommandResult execute(HttpServletRequest req) {
        switch (command) {
            case CONTACTS_ATTRIBUTE:
                return CommandResult.forward(CONTACTS_PAGE);
            case ABOUT_US_ATTRIBUTE:

                return CommandResult.forward(ABOUT_US_PAGE);
            case SIGN_IN_ATTRIBUTE:

                return CommandResult.forward(SIGN_IN_PAGE);
            case DEPARTMENTS_ATTRIBUTE:

                return CommandResult.forward(DEPARTMENTS_PAGE);
            default:
                req.setAttribute(ERROR_ATTRIBUTE, ERROR_MASSAGE);
                return CommandResult.forward(HOME_PAGE);
        }
    }
}
