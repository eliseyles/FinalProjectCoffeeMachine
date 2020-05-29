package by.training.khoroneko.service;

import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;

import java.util.List;

/**
 * Drink service. Service for add and manipulate database drinks.
 */
public interface OrderService {

    /**
     * Add order.
     *
     * @param order order.
     * @throws ServiceException if
     *                          <p>
     *                          {@link by.training.khoroneko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void addOrder(Order order) throws ServiceException;

    /**
     * Find all orders by user id.
     *
     * @param user user.
     * @return list of orders
     * @throws ServiceException if
     *                          <p>
     *                          {@link by.training.khoroneko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    List<Order> getAllOrdersByUserId(User user) throws ServiceException;

    /**
     * Delete order.
     *
     * @param order order.
     * @throws ServiceException if
     *                          <p>
     *                          {@link by.training.khoroneko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void deleteOrder(Order order) throws ServiceException;

    /**
     * Checkout user orders.
     *
     * @param user user.
     * @throws ServiceException if
     *                          <p>
     *                          {@link by.training.khoroneko.exception.DAOException}
     *                          has occurred
     *                          when working with database.
     */
    void checkoutCart(User user) throws ServiceException;
}
