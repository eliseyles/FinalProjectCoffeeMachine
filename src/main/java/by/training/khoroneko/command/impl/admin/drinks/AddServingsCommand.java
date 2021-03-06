package by.training.khoroneko.command.impl.admin.drinks;

import by.training.khoroneko.builder.DrinkBuilder;
import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.JSPParameter;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServingsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.INSTANCE.getDrinkService()
                    .update(new DrinkBuilder()
                            .setId(Integer.parseInt(request.getParameter(JSPParameter.DRINK_ID.getValue())))
                            .setServingNumber(Integer.parseInt(request.getParameter(JSPParameter.DRINK_SERVING_NUMBER.getValue())))
                            .getResult());
            return Pages.DRINK_MANAGEMENT_JSP.getValue();
        } catch (ServiceException ex) {
            if (ex.getCause() instanceof DAOException) {
                return Pages.ERROR_500_JSP.getValue();
            } else {
                request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
                return Pages.DRINK_ADDING_SERVINGS_JSP.getValue();
            }
        }
    }
}
