package by.training.khoroneko.command.impl;

import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DrinkListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(Attribute.DRINK_LIST.getValue(), ServiceFactory.INSTANCE.getDrinkService().getAll());
            return Pages.DRINK_LIST_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MESSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_500_JSP.getValue();
        }
    }
}
