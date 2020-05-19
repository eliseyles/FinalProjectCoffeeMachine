package by.training.khoroneko.command.impl.user.card;

import by.training.khoroneko.builder.CardAccountBuilder;
import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;
import by.training.khoroneko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCardCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.INSTANCE.getUserService();
        try {
            User user = new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .setCardAccount(
                            new CardAccountBuilder()
                                    .setId(Integer.parseInt(request.getParameter(JSPParameter.CARD_ID.getValue())))
                                    .setCardNumber(request.getParameter(JSPParameter.CARD_NUMBER.getValue()))
                                    .getResult())
                    .getResult();
            userService.updateCardInfoById(user);
            request.getSession().setAttribute(Attribute.USER.getValue(),
                    userService.findById(new UserBuilder()
                            .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                            .getResult()));
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.EDIT_CARD_JSP.getValue();
        }
    }
}
