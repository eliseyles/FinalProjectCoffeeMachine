package by.training.khoroneko.dao;

import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;

public interface UserDAO {
    User findByEmailAndPassword(User user) throws DAOException;
}
