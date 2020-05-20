package by.training.khoroneko.filter;

import by.training.khoroneko.command.*;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.factory.CommandFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "OrderFilter", urlPatterns = {"/coffee_machine"})
public class OrderFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute(Attribute.USER.getValue());
        Command command = CommandFactory.INSTANCE.getCommand(req.getParameter(JSPParameter.COMMAND.getValue()));
        if (command.equals(CommandParameter.ADD_DRINK_TO_CART.getCommand())) {
            if (user == null) {
                req.getRequestDispatcher(Pages.SIGN_IN_JSP.getValue()).forward(req, resp);
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
