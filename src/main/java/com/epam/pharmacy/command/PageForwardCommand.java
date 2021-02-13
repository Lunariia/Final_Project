package com.epam.pharmacy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageForwardCommand implements Command{

    private final String page;

    public PageForwardCommand(String page) {
        this.page = page;
    }


    @Override
    public CommandResult execute(HttpServletRequest req) {
        return CommandResult.forward(page);
    }
}
