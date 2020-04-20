package by.training.khoroneko.command.redirect;

import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.entity.Role;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
//            todo add password hashing
            new UserServiceImpl().register(new UserBuilder()
                    .setName(request.getParameter(JSPParameter.USER_NAME.getValue()))
                    .setEmail(request.getParameter(JSPParameter.USER_EMAIL.getValue()))
                    .setPassword(request.getParameter(JSPParameter.USER_PASSWORD.getValue()))
                    .setActivity(true)
                    .setRole(Role.USER.getId())
                    .getResult());
            return Pages.INDEX_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(JSPParameter.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.REGISTRATION_JSR.getValue();
        }
    }
}
