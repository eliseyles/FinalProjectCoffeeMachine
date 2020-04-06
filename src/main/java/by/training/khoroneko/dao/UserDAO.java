package by.training.khoroneko.dao;

import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;

public interface UserDAO {
    int create(User newUser) throws DAOException;

    void update(User newUser) throws DAOException;

    void delete(User user) throws DAOException;

    User findById(int id) throws DAOException;

    User findByEmail(String email) throws DAOException;
}
