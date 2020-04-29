package by.training.khoroneko.command.impl;

import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.entity.Role;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;
import by.training.khoroneko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserProfileCommand implements Command {
    private UserService userService = ServiceFactory.INSTANCE.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
//            todo add password hashing
            String password = request.getParameter(JSPParameter.USER_PASSWORD.getValue()).equals("")
                    ? ((User)request.getSession().getAttribute(Attribute.USER.getValue())).getPassword()
                    : request.getParameter(JSPParameter.USER_PASSWORD.getValue());
            userService.updateUserInfoById(new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .setName(request.getParameter(JSPParameter.USER_NAME.getValue()))
                    .setEmail(request.getParameter(JSPParameter.USER_EMAIL.getValue()))
                    .setPassword(password)
                    .getResult());
            request.getSession().setAttribute(Attribute.USER.getValue(), userService.findById(new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .getResult()));
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.EDIT_PROFILE_JSP.getValue();
        }
    }
}
