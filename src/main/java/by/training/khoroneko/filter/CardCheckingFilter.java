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

@WebFilter(filterName = "CardCheckingFilter", urlPatterns = {"/coffee_machine"})
public class CardCheckingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute(Attribute.USER.getValue());
        Command command = new CommandFactory().getCommand(req.getParameter(JSPParameter.COMMAND.getValue()));
        if (command.equals(CommandParameter.CHECKOUT_CART.getCommand())) {
            if (user.getCardAccount().getCardNumber() == null) {
                req.getRequestDispatcher(Pages.ADD_CARD_JSP.getValue()).forward(req, resp);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
