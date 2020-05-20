package by.training.khoroneko.command.impl.user.card;

import by.training.khoroneko.builder.CardAccountBuilder;
import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;
import by.training.khoroneko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddCardCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.INSTANCE.getUserService();
        try {
            User user = new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .setCardAccount(
                            new CardAccountBuilder()
                            .setCardNumber(request.getParameter(JSPParameter.CARD_NUMBER.getValue()))
                            .setAmount(BigDecimal.valueOf(Double.parseDouble(request.getParameter(JSPParameter.CARD_AMOUNT.getValue()))))
                            .getResult())
                    .getResult();
            userService.attachCardToUserById(user);
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            if (ex.getCause() instanceof DAOException) {
                return Pages.ERROR_500_JSP.getValue();
            } else {
                request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
                return Pages.ADD_CARD_JSP.getValue();
            }
        }
    }
}
