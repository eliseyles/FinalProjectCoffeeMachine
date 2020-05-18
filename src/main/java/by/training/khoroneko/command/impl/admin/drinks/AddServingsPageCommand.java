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

public class AddServingsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(Attribute.DRINK.getValue(), ServiceFactory.INSTANCE.getDrinkService()
                    .findById(new DrinkBuilder()
                            .setId(Integer.parseInt(request.getParameter(JSPParameter.DRINK_ID.getValue())))
                            .getResult()));
            return Pages.DRINK_ADDING_SERVINGS_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
