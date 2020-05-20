package by.training.khoroneko.command.impl.user;

import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserProfilePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute(Attribute.USER.getValue());
            request.setAttribute(Attribute.HISTORY.getValue(),
                    ServiceFactory.INSTANCE.getDrinkService().getPurchaseHistoryByUserId(user));
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
