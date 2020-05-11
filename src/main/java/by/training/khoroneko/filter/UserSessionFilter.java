package by.training.khoroneko.filter;

import by.training.khoroneko.command.*;
import by.training.khoroneko.entity.Role;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.factory.CommandFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "UserSessionFilter", urlPatterns = {"/coffee_machine"})
public class UserSessionFilter implements Filter {
    private static final EnumSet<CommandParameter> USER_COMMANDS =
            EnumSet.range(CommandParameter.USER_PROFILE, CommandParameter.CHECKOUT_CART);

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        CommandParameter command = CommandParameter.valueOf(req.getParameter(JSPParameter.COMMAND.getValue()));
        User user = (User) session.getAttribute(Attribute.USER.getValue());
        if (USER_COMMANDS.contains(command) && user == null) {
            req.getRequestDispatcher(Pages.SIGN_IN_JSP.getValue()).forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }
}

