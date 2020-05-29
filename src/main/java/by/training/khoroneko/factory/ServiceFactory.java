package by.training.khoroneko.factory;

import by.training.khoroneko.service.DrinkService;
import by.training.khoroneko.service.OrderService;
import by.training.khoroneko.service.UserService;
import by.training.khoroneko.service.impl.DrinkServiceImpl;
import by.training.khoroneko.service.impl.OrderServiceImpl;
import by.training.khoroneko.service.impl.UserServiceImpl;

/**
 * Factory for services.
 */
public enum ServiceFactory {
    INSTANCE;

    private UserService userService = new UserServiceImpl();
    private DrinkService drinkService = new DrinkServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public DrinkService getDrinkService() {
        return drinkService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
