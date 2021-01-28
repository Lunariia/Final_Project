package com.epam.pharmacy.filter;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.model.entity.Account;
import com.epam.pharmacy.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessControlFilter implements Filter {

    private static final Role ROLE_AS_DEFAULT = Role.GUEST;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);

        Role role = account == null ? ROLE_AS_DEFAULT : account.getRole();

        String command = req.getParameter(Parameter.COMMAND);
        if (command == null || role.hasAccess(command)) {
            filterChain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendError(403);
        }
    }

    @Override
    public void destroy() {
    }
}
