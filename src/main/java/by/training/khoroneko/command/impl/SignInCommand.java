package by.training.khoroneko.command.impl;

import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
//            todo add password hashing
            request.getSession().setAttribute(Attribute.USER.getValue(),
                    new UserServiceImpl()
                            .signIn(new UserBuilder()
                                    .setEmail(request.getParameter(JSPParameter.USER_EMAIL.getValue()))
                                    .setPassword(request.getParameter(JSPParameter.USER_PASSWORD.getValue()))
                                    .getResult()));
            return Pages.INDEX_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.SIGN_IN_JSP.getValue();
        }
    }
}
