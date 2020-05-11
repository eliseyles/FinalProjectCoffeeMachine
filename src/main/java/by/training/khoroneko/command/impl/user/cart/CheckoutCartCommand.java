package by.training.khoroneko.command.impl.user.cart;

import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckoutCartCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute(Attribute.USER.getValue());
            ServiceFactory.INSTANCE.getOrderService().checkoutCart(user);
            request.getSession().setAttribute(Attribute.USER.getValue(),
                    ServiceFactory.INSTANCE.getUserService().findById(user));
            return Pages.CART_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.CART_JSP.getValue();
        }
    }
}
