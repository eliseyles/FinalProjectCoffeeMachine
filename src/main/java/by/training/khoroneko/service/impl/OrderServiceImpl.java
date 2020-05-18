package by.training.khoroneko.service.impl;

import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.OrderDAO;
import by.training.khoroneko.dao.UserDAO;
import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ExceptionsValue;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.exception.ValidationException;
import by.training.khoroneko.factory.DAOFactory;
import by.training.khoroneko.service.OrderService;
import by.training.khoroneko.validation.OrderValidation;
import by.training.khoroneko.validation.PurchaseValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private AbstractCommonDAO<Order> orderDAO = DAOFactory.INSTANCE.getOrderDAO();
    private AbstractCommonDAO<User> userDAO = DAOFactory.INSTANCE.getUserDAO();
    private AbstractCommonDAO<Drink> drinkDAO = DAOFactory.INSTANCE.getDrinkDAO();
    private OrderValidation orderValidation = new OrderValidation();
    private PurchaseValidator purchaseValidator = new PurchaseValidator();

    @Override
    public void addOrder(Order order) throws ServiceException {
        try {
            orderValidation.isValidOrderData(order);
            orderDAO.create(order);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<Order> getAllOrdersByUserId(User user) throws ServiceException {
        try {
            return ((OrderDAO) orderDAO).findAllOrdersByUserId(user);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        }
    }

    @Override
    public void deleteOrder(Order order) throws ServiceException {
        try {
            orderValidation.isValidOrderId(order);
            orderDAO.delete(order);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void checkoutCart(User user) throws ServiceException {
        try {
            List<Order> cart = ((OrderDAO) orderDAO).findAllOrdersByUserId(user);
            List<Drink> drinks = drinkDAO.getAll();
            user = ((UserDAO) userDAO).findById(user);
            purchaseValidator.isValidPurchase(cart, drinks, user);
            ((OrderDAO) orderDAO).checkoutCart(cart);
        } catch (DAOException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ExceptionsValue.SERVER_ERROR.toString(), ex);
        } catch (ValidationException ex) {
            logger.error(ex.getMessage(), ex);
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}
