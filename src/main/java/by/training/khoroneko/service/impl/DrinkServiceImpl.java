package by.training.khoroneko.service.impl;

import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.DrinkDAO;
import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ExceptionsValue;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.exception.ValidationException;
import by.training.khoroneko.factory.DAOFactory;
import by.training.khoroneko.service.DrinkService;
import by.training.khoroneko.validation.DrinkValidator;
import by.training.khoroneko.validation.UserValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class DrinkServiceImpl implements DrinkService {

    private Logger logger = Logger.getLogger(DrinkServiceImpl.class);
    private AbstractCommonDAO<Drink> drinkDAO = DAOFactory.INSTANCE.getDrinkDAO();
    private DrinkValidator drinkValidator = new DrinkValidator();
    private UserValidator userValidator = new UserValidator();

    @Override
    public void add(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkData(drink);
            Drink existDrink = ((DrinkDAO) drinkDAO).findByTitleAndSizeAndPrice(drink);
            if (existDrink == null) {
                drinkDAO.create(drink);
            } else {
                drink.setId(existDrink.getId());
                update(drink);
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
    public void update(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidIdAndServingNumber(drink);
            Drink existDrink = ((DrinkDAO) drinkDAO).findById(drink);
            if (existDrink == null) {
                logger.error(ExceptionsValue.NOT_EXIST_DRINK.toString());
                throw new ServiceException(ExceptionsValue.NOT_EXIST_DRINK.toString());
            } else {
                drink.setServingNumber(drink.getServingNumber() + existDrink.getServingNumber());
                drinkDAO.update(drink);
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
    public void delete(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkId(drink);
            drinkDAO.delete(drink);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Drink> getAll() throws ServiceException {
        try {
            return drinkDAO.getAll();
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        }
    }

    @Override
    public Drink findById(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkId(drink);
            return ((DrinkDAO) drinkDAO).findById(drink);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Drink> getPurchaseHistoryByUserId(User user) throws ServiceException {
        try {
            userValidator.isValidUserId(user);
            return ((DrinkDAO) drinkDAO).findHistoryByUserId(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}

