package by.training.khoroneko.command.redirect;

import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message", "hello");
        return Pages.REGISTRATION_JSR.getValue();
    }
}
