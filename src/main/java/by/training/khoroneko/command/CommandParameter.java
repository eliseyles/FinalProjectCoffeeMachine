package by.training.khoroneko.command;

import by.training.khoroneko.command.redirect.IndexPageCommand;
import by.training.khoroneko.command.redirect.RegistrationCommand;

public enum CommandParameter {
    INDEX_PAGE(new IndexPageCommand()),
    REGISTRATION(new RegistrationCommand());

    Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
