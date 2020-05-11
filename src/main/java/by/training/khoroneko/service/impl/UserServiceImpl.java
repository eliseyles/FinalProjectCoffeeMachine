package by.training.khoroneko.service.impl;

import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.UserDAO;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.exception.ValidationException;
import by.training.khoroneko.factory.DAOFactory;
import by.training.khoroneko.service.UserService;
import by.training.khoroneko.validation.CardAccountValidator;
import by.training.khoroneko.validation.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class);
    private AbstractCommonDAO<User> userDAO = DAOFactory.INSTANCE.getUserDAO();
    private UserValidator userValidator = new UserValidator();
    private CardAccountValidator cardAccountValidator = new CardAccountValidator();

    @Override
    public User register(User user) throws ServiceException {
        try {
            userValidator.isValidUser(user);
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            if (((UserDAO) userDAO).findByEmail(user) == null) {
                userDAO.create(user);
                return user;
            } else {
                throw new ServiceException("Email is already taken");
            }
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while adding user", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid user data", ex);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            userDAO.update(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while updating user", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid user data", ex);
        }
    }

    @Override
    public void delete(User user) throws ServiceException {
        try {
            userValidator.isValidUser(user);
            userDAO.delete(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while deleting user", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid user data", ex);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException ex) {
            throw new ServiceException("Error while getting all users", ex);
        }
    }

    @Override
    public User signIn(User user) throws ServiceException {
        try {
            userValidator.isValidEmailAndPassword(user);
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            User userFromDB = ((UserDAO) userDAO).findByEmailAndPassword(user);
            if (userFromDB == null) {
                throw new ServiceException("Incorrect username or password");
            }
            return userFromDB;
        } catch (DAOException ex) {
            throw new ServiceException("Error while sign in", ex);
        } catch (ValidationException ex) {
            throw new ServiceException("Invalid user data", ex);
        }
    }

    @Override
    public User findById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            return ((UserDAO) userDAO).findById(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while getting user by id", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid user data", ex);
        }
    }

    @Override
    public void updateUserInfoById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            if (user.getPassword().equals("")) {
                userValidator.isValidUserWithoutPassword(user);
                user.setPassword(((UserDAO) userDAO).findById(user).getPassword());
            } else {
                userValidator.isValidUser(user);
                user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            }
            ((UserDAO) userDAO).updateUserInfoById(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while updating user info", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid user data", ex);
        }
    }

    @Override
    public void attachCardToUserById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            cardAccountValidator.isValidCardAccountData(user.getCardAccount());
            ((UserDAO) userDAO).attachCardToUserById(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while attaching card", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid data", ex);
        }
    }

    @Override
    public void updateCardInfoById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            cardAccountValidator.isValidCardAccountIdAndNumber(user.getCardAccount());
            if (((UserDAO) userDAO).findById(user).getCardAccount().getId() != user.getCardAccount().getId()) {
                throw new ServiceException("Invalid card data, user card mismatch with the entered card");
            }
            ((UserDAO) userDAO).updateCardInfoById(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while updating card", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid data", ex);
        }
    }

    @Override
    public void updateCardAmountById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            cardAccountValidator.isValidCardAccountIdAndAmount(user.getCardAccount());
            if (((UserDAO) userDAO).findById(user).getCardAccount().getId() != user.getCardAccount().getId()) {
                throw new ServiceException("Invalid card data, user card mismatch with the entered card");
            }
            BigDecimal currentAmount = ((UserDAO) userDAO).findById(user).getCardAccount().getAmount();
            user.getCardAccount().setAmount(currentAmount.add(user.getCardAccount().getAmount()));
            ((UserDAO) userDAO).updateCardAmountById(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while updating card amount", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid data", ex);
        }
    }

    @Override
    public void deleteCardFromUserById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            cardAccountValidator.isValidCardAccountId(user.getCardAccount());
            if (((UserDAO) userDAO).findById(user).getCardAccount().getId() != user.getCardAccount().getId()) {
                throw new ServiceException("Invalid card data, user card mismatch with the entered card");
            }
            ((UserDAO) userDAO).deleteCardFromUserById(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while deleting card", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid data", ex);
        }
    }
}
