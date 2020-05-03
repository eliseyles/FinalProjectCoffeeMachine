package by.training.khoroneko.service.impl;

import by.training.khoroneko.dao.OrderDAO;
import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.exception.ServiceException;
import by.training.khoroneko.factory.DAOFactory;
import by.training.khoroneko.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = DAOFactory.INSTANCE.getOrderDAO();

    @Override
    public List<Order> getAllOrdersByUserId(User user) throws ServiceException {
        try {
            return orderDAO.findAllOrdersByUserId(user);
        } catch (DAOException ex) {
            throw new ServiceException("Error while getting orders", ex);
        }
    }
}
