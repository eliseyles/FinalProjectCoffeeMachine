package by.training.khoroneko.command.impl.admin.users;

import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserEditCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(Attribute.USER_PROFILE.getValue(), ServiceFactory.INSTANCE.getUserService()
                    .findById(new UserBuilder()
                            .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                            .getResult()));
            return Pages.USER_EDIT_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }

    }
}
