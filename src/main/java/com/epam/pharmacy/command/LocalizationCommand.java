package com.epam.pharmacy.command;

import com.epam.pharmacy.constants.Attribute;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.model.Localization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LocalizationCommand implements Command {

    private static final String REFERER_HEADER = "Referer";

    @Override
    public CommandResult execute(HttpServletRequest req) {

        String language = req.getParameter(Parameter.LANGUAGE);
        Localization localization = Localization.valueOf(language);

        HttpSession session = req.getSession();
        session.setAttribute(Attribute.LOCALIZATION, localization);

        String page = req.getHeader(REFERER_HEADER);
        if (page == null) {
            page = req.getContextPath();
            return CommandResult.redirect(page);
        }
        return CommandResult.redirect(page);
    }
}
