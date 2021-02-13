package com.epam.pharmacy.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    CommandResult execute(HttpServletRequest req) throws Exception;
}
