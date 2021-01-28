 package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.CommandName;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.ServiceException;
import com.epam.pharmacy.logic.AccountService;
import com.epam.pharmacy.model.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String ERROR_MASSAGE = "Invalid data";
    private final AccountService accountService;

    public LoginCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException {

        String login = req.getParameter(Parameter.LOGIN);
        String password = req.getParameter(Parameter.PASSWORD);

        if (login == null || password == null) {
            throw new ServiceException("Incoming parameters are not correct!");
        }

        Optional<Account> found = accountService.authenticate(login, password);
        if (!found.isPresent()) {
            req.setAttribute(Attribute.MESSAGE, ERROR_MASSAGE);
            return CommandResult.forward(Page.LOGIN);
        }
        Account account = found.get();

        HttpSession session = req.getSession();
        session.setAttribute(Attribute.ACCOUNT, account);

        String contextPath = req.getContextPath();
        return CommandResult.redirect(Page.HOME_PAGE);
    }
}
