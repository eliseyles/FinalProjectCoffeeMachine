package by.training.khoroneko.factory;

import by.training.khoroneko.dao.impl.DrinkDAOImpl;
import by.training.khoroneko.dao.impl.OrderDAOImpl;
import by.training.khoroneko.dao.impl.UserDAOImpl;

public enum DAOFactory {
    INSTANCE;

    private UserDAOImpl userDAO = new UserDAOImpl();
    private DrinkDAOImpl drinkDAO = new DrinkDAOImpl();
    private OrderDAOImpl orderDAO = new OrderDAOImpl();

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public DrinkDAOImpl getDrinkDAO() {
        return drinkDAO;
    }

    public OrderDAOImpl getOrderDAO() {
        return orderDAO;
    }
}
