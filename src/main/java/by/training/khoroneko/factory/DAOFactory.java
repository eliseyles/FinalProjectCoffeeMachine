package by.training.khoroneko.factory;

import by.training.khoroneko.dao.impl.UserDAOImpl;

public enum DAOFactory {
    INSTANCE;

    private UserDAOImpl userDAO = new UserDAOImpl();

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

}
