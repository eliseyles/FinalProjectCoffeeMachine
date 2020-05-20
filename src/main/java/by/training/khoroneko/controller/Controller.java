package by.training.khoroneko.controller;

import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.exception.ConnectionPoolException;
import by.training.khoroneko.factory.CommandFactory;
import by.training.khoroneko.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/coffee_machine")

public class Controller extends HttpServlet {
    private Logger logger = Logger.getLogger(Controller.class);
    private static final CommandFactory COMMAND_FACTORY = CommandFactory.INSTANCE;
    private static final String URL = "%s%s?command=%s";

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.INSTANCE.init();
        } catch (ConnectionPoolException ex) {
            logger.fatal(ex);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(processRequest(req, resp)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nextPage = processRequest(req, resp);
        String next = Pages.getCommandNameByPath(nextPage);
        if (next.equals(Pages.ERROR_500_JSP.getCommandName())) {
            resp.sendError(Integer.parseInt(next));
        } else {
            if (req.getAttribute(Attribute.ERROR_MESSAGE.getValue()) != null) {
                req.getRequestDispatcher(nextPage).forward(req, resp);
            } else {
                resp.sendRedirect(String.format(URL, req.getContextPath(), req.getServletPath(), next));
            }
        }
    }

    private String processRequest(HttpServletRequest req, HttpServletResponse resp) {
        Command command = COMMAND_FACTORY.getCommand(req.getParameter(JSPParameter.COMMAND.getValue()));
        return command.execute(req, resp);
    }
}

