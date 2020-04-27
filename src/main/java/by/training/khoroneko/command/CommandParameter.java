package by.training.khoroneko.command;

import by.training.khoroneko.command.impl.*;
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
    UNBLOCK_USER(new UnblockUserCommand()),
    DRINK_LIST(new DrinkListCommand()),
    DRINK_MANAGEMENT(new DrinkManagementCommand()),
    ADD_DRINK(new AddDrinkCommand()),
    ADD(new AddCommand()),
    ADD_SERVINGS_PAGE(new AddServingsPageCommand()),
    ADD_SERVINGS(new AddServingsCommand()),
    USER_PROFILE(new UserProfilePageCommand());

    Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
