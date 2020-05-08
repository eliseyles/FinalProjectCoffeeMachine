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

@WebFilter(filterName = "PageRedirectSecurityFilter", urlPatterns = {"/page/*"})
public class PageRedirectSecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (req.getParameter(JSPParameter.COMMAND.getValue()) == null) {
            resp.sendRedirect(req.getContextPath() + "/coffee_machine?command=INDEX_PAGE");
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}

