package by.training.khoroneko.factory;

import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.CommandParameter;
import by.training.khoroneko.command.JSPParameter;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    public Command defineCommand(HttpServletRequest request) {
        return CommandParameter.valueOf(request.getParameter(JSPParameter.COMMAND.getValue())).getCommand();
    }

    public Command getCommand(String commandName) {
        return CommandParameter.valueOf(commandName).getCommand();
    }
}
