package by.training.khoroneko.dao;

import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;

import java.util.List;

public interface OrderDAO {
    List<Order> findAllOrdersByUserId(User user) throws DAOException;

    void checkoutCart(List<Order> cart) throws DAOException;
}
