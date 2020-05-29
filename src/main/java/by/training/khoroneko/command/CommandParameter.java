package by.training.khoroneko.command;

import by.training.khoroneko.command.impl.*;
import by.training.khoroneko.command.impl.admin.drinks.AddDrinkCommand;
import by.training.khoroneko.command.impl.admin.drinks.AddServingsCommand;
import by.training.khoroneko.command.impl.admin.drinks.AddServingsPageCommand;
import by.training.khoroneko.command.impl.admin.drinks.DrinkManagementCommand;
import by.training.khoroneko.command.impl.admin.users.BlockUserCommand;
import by.training.khoroneko.command.impl.admin.users.UnblockUserCommand;
import by.training.khoroneko.command.impl.admin.users.UserEditCommand;
import by.training.khoroneko.command.impl.admin.users.UserListCommand;
import by.training.khoroneko.command.impl.user.*;
import by.training.khoroneko.command.impl.user.card.AddCardCommand;
import by.training.khoroneko.command.impl.user.card.AddMoneyCommand;
import by.training.khoroneko.command.impl.user.card.DeleteCardCommand;
import by.training.khoroneko.command.impl.user.card.EditCardCommand;
import by.training.khoroneko.command.impl.user.cart.AddDrinkToCartCommand;
import by.training.khoroneko.command.impl.user.cart.CartPageCommand;
import by.training.khoroneko.command.impl.user.cart.CheckoutCartCommand;
import by.training.khoroneko.command.impl.user.cart.DeleteDrinkFromCartCommand;
import by.training.khoroneko.command.redirect.*;
import by.training.khoroneko.command.redirect.admin.AddDrinkPageCommand;
import by.training.khoroneko.command.redirect.user.*;

/**
 * Represent request command attribute parameters.
 */
public enum CommandParameter {
    INDEX_PAGE(new IndexPageCommand()),
    DRINK_LIST(new DrinkListCommand()),

    REGISTRATION_PAGE(new RegistrationPageCommand()),
    REGISTER(new RegisterCommand()),
    SIGN_IN_PAGE(new SignInPageCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),

    USER_LIST(new UserListCommand()),
    USER_EDIT(new UserEditCommand()),
    BLOCK_USER(new BlockUserCommand()),
    UNBLOCK_USER(new UnblockUserCommand()),

    DRINK_MANAGEMENT(new DrinkManagementCommand()),
    ADD_DRINK_PAGE(new AddDrinkPageCommand()),
    ADD_DRINK(new AddDrinkCommand()),
    ADD_SERVINGS_PAGE(new AddServingsPageCommand()),
    ADD_SERVINGS(new AddServingsCommand()),

    USER_PROFILE(new UserProfilePageCommand()),
    EDIT_PROFILE_PAGE(new EditProfilePageCommand()),
    EDIT_USER_PROFILE(new EditUserProfileCommand()),

    ADD_CARD_PAGE(new AddCardPageCommand()),
    ADD_CARD(new AddCardCommand()),
    EDIT_CARD_PAGE(new EditCardPageCommand()),
    EDIT_CARD(new EditCardCommand()),
    ADD_MONEY_PAGE(new AddMoneyPageCommand()),
    ADD_MONEY(new AddMoneyCommand()),
    DELETE_CARD(new DeleteCardCommand()),

    CART_PAGE(new CartPageCommand()),
    ADD_DRINK_TO_CART(new AddDrinkToCartCommand()),
    DELETE_DRINK_FROM_CART(new DeleteDrinkFromCartCommand()),
    CHECKOUT_CART(new CheckoutCartCommand());

    Command command;

    CommandParameter(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
