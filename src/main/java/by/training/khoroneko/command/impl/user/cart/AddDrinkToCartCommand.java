package by.training.khoroneko.command.impl.user.cart;

import by.training.khoroneko.builder.DrinkBuilder;
import by.training.khoroneko.builder.OrderBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDrinkToCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute(Attribute.USER.getValue()) == null) {
                return Pages.SIGN_IN_JSP.getValue();
            } else {
                ServiceFactory.INSTANCE.getOrderService().addOrder(
                        new OrderBuilder()
                                .setDrink(new DrinkBuilder()
                                        .setId(Integer.parseInt(request.getParameter(JSPParameter.DRINK_ID.getValue())))
                                        .getResult())
                                .setUser((User)request.getSession().getAttribute(Attribute.USER.getValue()))
                                .getResult());

                request.setAttribute(Attribute.DRINK_LIST.getValue(), ServiceFactory.INSTANCE.getDrinkService().getAll());
                return Pages.DRINK_LIST_JSP.getValue();
            }
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_JSP.getValue();
        }
    }
}
