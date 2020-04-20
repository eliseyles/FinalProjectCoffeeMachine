package by.training.khoroneko.factory;

import by.training.khoroneko.service.UserService;
import by.training.khoroneko.service.impl.UserServiceImpl;

public enum ServiceFactory {
    INSTANCE;

    private UserService userService = new UserServiceImpl();

    public UserService getUserService() {
        return userService;
    }
}
