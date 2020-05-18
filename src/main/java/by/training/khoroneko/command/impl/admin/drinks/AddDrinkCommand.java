package by.training.khoroneko.command.impl.admin.drinks;

import by.training.khoroneko.builder.DrinkBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
public class AddDrinkCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.INSTANCE.getDrinkService().add(new DrinkBuilder()
                    .setTitle(request.getParameter(JSPParameter.DRINK_TITLE.getValue()))
                    .setDrinkSize(request.getParameter(JSPParameter.DRINK_VOLUME.getValue()))
                    .setPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter(JSPParameter.DRINK_PRICE.getValue()))))
                    .setServingNumber(Integer.parseInt(request.getParameter(JSPParameter.DRINK_SERVING_NUMBER.getValue())))
                    .getResult());
            request.setAttribute(Attribute.DRINK_LIST.getValue(), ServiceFactory.INSTANCE.getDrinkService().getAll());
            return Pages.DRINK_MANAGEMENT_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_JSP.getValue();
        }
    }
}
