package by.training.khoroneko.command.impl;

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
import java.math.BigDecimal;

public class AddMoneyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.INSTANCE.getUserService();
        try {
            User user = new UserBuilder()
                    .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                    .setCardAccount(
                            new CardAccountBuilder()
                                    .setId(Integer.parseInt(request.getParameter(JSPParameter.CARD_ID.getValue())))
                                    .setAmount(BigDecimal.valueOf(
                                                Double.parseDouble(
                                                        request.getParameter(JSPParameter.ADDING_CARD_AMOUNT.getValue()))))
                                    .getResult())
                    .getResult();
            userService.updateCardAmountById(user);
            request.getSession().setAttribute(Attribute.USER.getValue(),
                    userService.findById(new UserBuilder()
                            .setId(Integer.parseInt(request.getParameter(JSPParameter.USER_ID.getValue())))
                            .getResult()));
            return Pages.USER_PROFILE_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.ADD_MONEY_JSP.getValue();
        }
    }
}
