package by.training.khoroneko.service.impl;

import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.UserDAO;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ExceptionsValue;
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
    public void register(User user) throws ServiceException {
        try {
            userValidator.isValidUser(user);
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            if (((UserDAO) userDAO).findByEmail(user) == null) {
                userDAO.create(user);
            } else {
                throw new ServiceException(ExceptionsValue.BOOKED_EMAIL.toString());
            }
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            userDAO.update(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(User user) throws ServiceException {
        try {
            userValidator.isValidUser(user);
            userDAO.delete(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            List<User> users = userDAO.getAll();
            for (User user : users) {
                user.setPassword(null);
            }
            return users;
        } catch (DAOException ex) {
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        }
    }

    @Override
    public User signIn(User user) throws ServiceException {
        try {
            userValidator.isValidEmailAndPassword(user);
            user.setPassword(DigestUtils.sha1Hex(user.getPassword()));
            User userFromDB = ((UserDAO) userDAO).findByEmailAndPassword(user);
            if (userFromDB == null) {
                logger.error(ExceptionsValue.INCORRECT_SIGN_IN_DATA.toString());
                throw new ServiceException(ExceptionsValue.INCORRECT_SIGN_IN_DATA.toString());
            }
            if (!userFromDB.getActivity()) {
                logger.error(ExceptionsValue.BLOCKED_USER.toString());
                throw new ServiceException(ExceptionsValue.BLOCKED_USER.toString());
            }
            userFromDB.setPassword(null);
            return userFromDB;
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public User findById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            User userFromDB = ((UserDAO) userDAO).findById(user);
            userFromDB.setPassword(null);
            return userFromDB;
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
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
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void attachCardToUserById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            cardAccountValidator.isValidCardAccountData(user.getCardAccount());
            ((UserDAO) userDAO).attachCardToUserById(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateCardInfoById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            cardAccountValidator.isValidCardAccountIdAndNumber(user.getCardAccount());
            if (((UserDAO) userDAO).findById(user).getCardAccount().getId() != user.getCardAccount().getId()) {
                logger.error(ExceptionsValue.USER_CARD_MISMATCHING_EXCEPTION.toString());
                throw new ServiceException(ExceptionsValue.USER_CARD_MISMATCHING_EXCEPTION.toString());
            }
            ((UserDAO) userDAO).updateCardInfoById(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateCardAmountById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            cardAccountValidator.isValidCardAccountIdAndAmount(user.getCardAccount());
            if (((UserDAO) userDAO).findById(user).getCardAccount().getId() != user.getCardAccount().getId()) {
                logger.error(ExceptionsValue.USER_CARD_MISMATCHING_EXCEPTION.toString());
                throw new ServiceException(ExceptionsValue.USER_CARD_MISMATCHING_EXCEPTION.toString());
            }
            BigDecimal currentAmount = ((UserDAO) userDAO).findById(user).getCardAccount().getAmount();
            user.getCardAccount().setAmount(currentAmount.add(user.getCardAccount().getAmount()));
            ((UserDAO) userDAO).updateCardAmountById(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteCardFromUserById(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            cardAccountValidator.isValidCardAccountId(user.getCardAccount());
            if (((UserDAO) userDAO).findById(user).getCardAccount().getId() != user.getCardAccount().getId()) {
                logger.error(ExceptionsValue.USER_CARD_MISMATCHING_EXCEPTION.toString());
                throw new ServiceException(ExceptionsValue.USER_CARD_MISMATCHING_EXCEPTION.toString());
            }
            ((UserDAO) userDAO).deleteCardFromUserById(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}
