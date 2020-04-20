package by.training.khoroneko.service.impl;

import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.DrinkDAO;
import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.exception.ValidationException;
import by.training.khoroneko.factory.DAOFactory;
import by.training.khoroneko.service.DrinkService;
import by.training.khoroneko.validation.DrinkValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class DrinkServiceImpl implements DrinkService {

    private Logger logger = Logger.getLogger(DrinkServiceImpl.class);
    private AbstractCommonDAO<Drink> drinkDAO = DAOFactory.INSTANCE.getDrinkDAO();
    private DrinkValidator drinkValidator = new DrinkValidator();

    @Override
    public void add(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkData(drink);
            Drink existDrink = ((DrinkDAO) drinkDAO).findByTitleAndSize(drink);
            if (existDrink == null) {
                drinkDAO.create(drink);
            } else {
                drink.setId(existDrink.getId());
                update(drink);
            }
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while adding drink", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid drink data", ex);
        }
    }

    @Override
    public void update(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidIdAndServingNumber(drink);
            Drink existDrink = ((DrinkDAO) drinkDAO).findById(drink);
            if (existDrink == null) {
                throw new ServiceException("Finding drink doesn't exist");
            } else {
                drink.setServingNumber(drink.getServingNumber() + existDrink.getServingNumber());
                drinkDAO.update(drink);
            }
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while adding drink", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid drink data", ex);
        }
    }

    @Override
    public void delete(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkId(drink);
            drinkDAO.delete(drink);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while deleting drink", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid drink data", ex);
        }
    }

    @Override
    public List<Drink> getAll() throws ServiceException {
        try {
            return drinkDAO.getAll();
        } catch (DAOException ex) {
            throw new ServiceException("Error while getting all drinks", ex);
        }
    }

    @Override
    public Drink findById(Drink drink) throws ServiceException {
        try {
            drinkValidator.isValidDrinkId(drink);
            return ((DrinkDAO)drinkDAO).findById(drink);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while finding drink by id", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid drink data", ex);
        }
    }
}
