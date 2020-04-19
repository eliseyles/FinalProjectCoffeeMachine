package by.training.khoroneko.service.impl;

import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.UserDAO;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.exception.ValidationException;
import by.training.khoroneko.factory.DAOFactory;
import by.training.khoroneko.service.UserService;
import by.training.khoroneko.validation.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {

    AbstractCommonDAO userDAO = DAOFactory.INSTANCE.getUserDAO();
    UserValidator userValidator = new UserValidator();
//    todo add logger

    @Override
    public User register(User user) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Invalid user data");
        }
        try {
            userValidator.isValidUser(user);
            if (((UserDAO) userDAO).findByEmail(user) == null) {
                userDAO.create(user);
                return user;
            } else {
                throw new ServiceException("Email is already taken");
            }
        } catch (DAOException ex) {
            throw new ServiceException("Error while adding user");
        } catch (ValidationException ex) {
            throw new ServiceException("Invalid user data");
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Invalid user data");
        }
        try {
            userValidator.isValidUserId(user);
            userDAO.update(user);
            return user;
        } catch (DAOException ex) {
            throw new ServiceException("Error while updating user");
        } catch (ValidationException ex) {
            throw new ServiceException("Invalid user data");
        }
    }

    @Override
    public void delete(User user) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Invalid user data");
        }
        try {
            userValidator.isValidUser(user);
            userDAO.delete(user);
        } catch (DAOException ex) {
            throw new ServiceException("Error while deleting user");
        } catch (ValidationException ex) {
            throw new ServiceException("Invalid user data");
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException ex) {
            throw new ServiceException("Error while getting all users");
        }
    }

    @Override
    public User signIn(User user) throws ServiceException {
        try {
            userValidator.isValidEmailAndPassword(user);
            return ((UserDAO) userDAO).findByEmailAndPassword(user);
        } catch (DAOException ex) {
            throw new ServiceException("Error while sign in");
        } catch (ValidationException ex) {
            throw new ServiceException("Invalid user data");
        }
    }

    @Override
    public User findById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            return ((UserDAO) userDAO).findById(user);
        } catch (DAOException ex) {
            throw new ServiceException("Error while getting user by id");
        } catch (ValidationException ex) {
            throw new ServiceException("Invalid user data");
        }
    }
}