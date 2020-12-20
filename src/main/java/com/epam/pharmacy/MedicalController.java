package com.epam.pharmacy;

import com.epam.pharmacy.command.Command;
import com.epam.pharmacy.command.CommandFactory;
import com.epam.pharmacy.command.CommandResult;
import com.epam.pharmacy.connection.ConnectionPool;
import com.epam.pharmacy.constants.Parameter;
import com.epam.pharmacy.logic.PageNotFoundException;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MedicalController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String commandRequest = req.getParameter(Parameter.COMMAND);
            Command command = CommandFactory.create(commandRequest);
            CommandResult commandResult = command.execute(req);
            String page = commandResult.getPage();

            if (commandResult.isRedirect()) {
                resp.sendRedirect(page);
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
                requestDispatcher.forward(req, resp);
            }
        } catch (ServiceException | ServletException | IOException | com.epam.pharmacy.logic.ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            resp.sendError(500);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.destroy();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        super.destroy();
    }
}
