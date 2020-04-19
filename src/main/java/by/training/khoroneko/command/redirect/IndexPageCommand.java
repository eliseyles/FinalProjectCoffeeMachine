package by.training.khoroneko.command.redirect;

import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.INDEX_JSP.getValue();
    }
}
