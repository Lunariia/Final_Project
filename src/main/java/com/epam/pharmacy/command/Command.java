package com.epam.pharmacy.command;

import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute(HttpServletRequest req) throws ServiceException, com.epam.pharmacy.logic.ServiceException;
}
