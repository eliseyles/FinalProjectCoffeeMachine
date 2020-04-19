package by.training.khoroneko.command;

import by.training.khoroneko.command.redirect.*;

public enum CommandParameter {
    INDEX_PAGE(new IndexPageCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    REGISTER(new RegisterCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_IN_PAGE(new SignInPageCommand()),
    SIGN_OUT(new SignOutCommand()),
    USER_LIST(new UserListCommand()),
    USER_EDIT(new UserEditCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand());

    Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}