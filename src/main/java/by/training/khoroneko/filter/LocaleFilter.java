package by.training.khoroneko.filter;

import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    private static final String ENCODING = "UTF-8";
    private static final String ENGLISH = "en";


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(ENCODING);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        if (session.getAttribute(Attribute.LANGUAGE.getValue()) == null) {
            session.setAttribute(Attribute.LANGUAGE.getValue(), ENGLISH);
        }
        String language = req.getParameter(JSPParameter.LANGUAGE.getValue());
        if (language != null) {
            session.setAttribute(Attribute.LANGUAGE.getValue(), new Locale(language));
            req.getRequestDispatcher(Pages.INDEX_JSP.getValue()).forward(req, resp);
            return;
        }
        chain.doFilter(request, response);

    }
}

