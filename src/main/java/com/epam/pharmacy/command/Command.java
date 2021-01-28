package com.epam.pharmacy.command;

import com.epam.pharmacy.logic.ServiceException;
import javax.servlet.http.HttpServletRequest;

public interface Command {

    CommandResult execute(HttpServletRequest req) throws Exception;
}
