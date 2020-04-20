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

public class BlockUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(Attribute.USER_PROFILE.getValue(), new UserServiceImpl().update(new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .setActivity(false)
                    .getResult()));
            return Pages.USER_EDIT_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_JSP.getValue();
        }
    }
}
