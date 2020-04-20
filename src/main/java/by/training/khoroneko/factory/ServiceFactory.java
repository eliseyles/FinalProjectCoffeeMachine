package by.training.khoroneko.factory;

import by.training.khoroneko.service.DrinkService;
import by.training.khoroneko.service.UserService;
import by.training.khoroneko.service.impl.DrinkServiceImpl;
import by.training.khoroneko.service.impl.UserServiceImpl;

public enum ServiceFactory {
    INSTANCE;

    private UserService userService = new UserServiceImpl();
    private DrinkService drinkService = new DrinkServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public DrinkService getDrinkService() {
        return drinkService;
    }
}
