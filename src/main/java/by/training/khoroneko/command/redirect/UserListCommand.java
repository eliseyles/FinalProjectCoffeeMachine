package by.training.khoroneko.command.redirect;

import by.training.khoroneko.command.Attribute;
import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.Pages;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> userList = new UserServiceImpl().getAll();
            request.setAttribute(Attribute.USER_LIST.getValue(), userList);
            return Pages.USER_LIST_JSP.getValue();
        } catch (ServiceException ex) {
            request.setAttribute(Attribute.ERROR_MASSAGE.getValue(), ex.getMessage());
            return Pages.ERROR_JSP.getValue();
        }
    }
}
