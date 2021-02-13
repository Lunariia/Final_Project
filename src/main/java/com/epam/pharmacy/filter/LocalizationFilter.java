package com.epam.pharmacy.filter;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.model.Localization;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        Localization currentLocalization = (Localization) session.getAttribute(Attribute.LOCALIZATION);
        if (currentLocalization == null) {
            session.setAttribute(Attribute.LOCALIZATION, Localization.EN);
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
