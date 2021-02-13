package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.logic.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand  implements Command{

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, ServiceException {
        HttpSession session = req.getSession();
        session.invalidate();
        return CommandResult.redirect(Page.INDEX);
    }
}
