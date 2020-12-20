package com.epam.pharmacy.command;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.command.CommandResult;
import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Page;
import com.epam.pharmacy.dao.manager.DaoConnectionManagerFactory;
import com.epam.pharmacy.logic.ServiceException;
import com.epam.pharmacy.login.AccountService;
import com.epam.pharmacy.login.AccountServiceImpl;
import com.epam.pharmacy.login.LoginService;
import com.epam.pharmacy.model.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String HOME_PAGE = "WEB-INF/view/home.jsp";
    private static final String LOGIN_PAGE = "index.jsp";
    private static final String ERROR_ATTRIBUTE = "errorMessage";
    private static final String ERROR_MASSAGE = "Invalid data";
    private static LoginService LOGIN_SERVICE = new LoginService();
    private static AccountService accountService;

    public LoginCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException {

        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

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
        return CommandResult.redirect(HOME_PAGE);
    }
}
