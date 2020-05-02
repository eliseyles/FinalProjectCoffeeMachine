package by.training.khoroneko.service;

import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;

import java.util.List;

public interface UserService {
    User register(User user) throws ServiceException;

    void update(User user) throws ServiceException;

    void delete(User user) throws ServiceException;

    List<User> getAll() throws ServiceException;

    User signIn(User user) throws ServiceException;

    User findById(User user) throws ServiceException;

    void updateUserInfoById(User user) throws ServiceException;

    void attachCardToUserById(User user) throws ServiceException;

    void updateCardInfoById(User user) throws ServiceException;
}
