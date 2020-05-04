package by.training.khoroneko.service.impl;

import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.OrderDAO;
import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.exception.ValidationException;
import by.training.khoroneko.factory.DAOFactory;
import by.training.khoroneko.service.OrderService;
import by.training.khoroneko.validation.OrderValidation;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private AbstractCommonDAO<Order> orderDAO = DAOFactory.INSTANCE.getOrderDAO();
    private OrderValidation orderValidation = new OrderValidation();

    @Override
    public void addOrder(Order order) throws ServiceException {
        try {
            orderValidation.isValidOrderData(order);
            orderDAO.create(order);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while adding order", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid order data", ex);
        }
    }

    @Override
    public List<Order> getAllOrdersByUserId(User user) throws ServiceException {
        try {
            return ((OrderDAO)orderDAO).findAllOrdersByUserId(user);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while getting orders", ex);
        }
    }

    @Override
    public void deleteOrder(Order order) throws ServiceException{
        try {
            orderValidation.isValidOrderId(order);
            orderDAO.delete(order);
        } catch (DAOException ex) {
            logger.error(ex);
            throw new ServiceException("Error while deleting order", ex);
        } catch (ValidationException ex) {
            logger.error(ex);
            throw new ServiceException("Invalid order data", ex);
        }
    }
}
