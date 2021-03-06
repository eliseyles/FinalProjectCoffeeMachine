package by.training.khoroneko.command.impl.user.cart;

import by.training.khoroneko.builder.OrderBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDrinkFromCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {

            ServiceFactory.INSTANCE.getOrderService().deleteOrder(
                    new OrderBuilder()
                            .setId(Integer.parseInt(request.getParameter(JSPParameter.DRINK_ID.getValue())))
                            .getResult());
            return Pages.CART_JSP.getValue();

        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
