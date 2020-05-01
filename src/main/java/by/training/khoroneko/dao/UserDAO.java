package by.training.khoroneko.dao;

import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;

public interface UserDAO {
    User findByEmailAndPassword(User user) throws DAOException;

    User findByEmail(User user) throws DAOException;

    User findById(User user) throws DAOException;

    void updateUserInfoById(User user) throws DAOException;

    int addCard(User user) throws DAOException;

    void attachCardToUserById(User user) throws DAOException;
}
