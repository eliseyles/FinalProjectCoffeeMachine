package by.training.khoroneko.controller;

import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.exception.ConnectionPoolException;
import by.training.khoroneko.factory.CommandFactory;
import by.training.khoroneko.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/coffee_machine")
@MultipartConfig(maxFileSize = 10240)

public class Controller extends HttpServlet {
    private Logger logger = Logger.getLogger(Controller.class);
    private static final CommandFactory COMMAND_FACTORY = CommandFactory.INSTANCE;

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
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = COMMAND_FACTORY.getCommand(req.getParameter(JSPParameter.COMMAND.getValue()));
        String nextPagePath = command.execute(req, resp);
        req.getRequestDispatcher(nextPagePath).forward(req, resp);
    }
}

