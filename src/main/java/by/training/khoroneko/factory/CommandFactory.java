package by.training.khoroneko.factory;

import by.training.khoroneko.command.Command;
import by.training.khoroneko.command.CommandParameter;

public enum CommandFactory {
    INSTANCE;

    public Command getCommand(String commandName) {
        return CommandParameter.valueOf(commandName).getCommand();
    }
}
