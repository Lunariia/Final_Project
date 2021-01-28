package com.epam.pharmacy.command;

import com.epam.pharmacy.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class AboutUsCommand implements Command{

    private static final String ABOUT_US_PAGE = "WEB-INF/view/homeAbout.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException{
        return CommandResult.redirect(ABOUT_US_PAGE);
    }
}
