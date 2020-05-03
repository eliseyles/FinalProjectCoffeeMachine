package by.training.khoroneko.service;

import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;

import java.util.List;

public interface OrderService {
    void addOrder(Order order) throws ServiceException;

    List<Order> getAllOrdersByUserId(User user) throws ServiceException;
}
