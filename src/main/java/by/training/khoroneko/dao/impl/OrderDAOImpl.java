package by.training.khoroneko.dao.impl;

import by.training.khoroneko.builder.DrinkBuilder;
import by.training.khoroneko.builder.OrderBuilder;
import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.OrderDAO;
import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends AbstractCommonDAO<Order> implements OrderDAO {

    private Logger logger = Logger.getLogger(OrderDAOImpl.class);

    private static final String INSERT_ORDER =
            "INSERT INTO `drink_shopping_cart` (`user_id`, `drinks_id`) VALUES (?, ?)";

    private static final String UPDATE_ORDER_BY_ID = "UPDATE `drink_shopping_cart` SET `drinks_id`=? WHERE `id` = ?";

    private static final String DELETE_ORDER_BY_ID = "DELETE FROM `drink_shopping_cart` WHERE `id` = ?";

    private static final String FIND_ALL_ORDERS =
            "SELECT `drink_shopping_cart`.`id`, `user_id`, `drinks_id`, `title`, `price`, `volume`" +
                    "FROM `drink_shopping_cart` JOIN `drinks` ON `drink_shopping_cart`.`drinks_id`=`drinks`.`id`" +
                    "JOIN `drink_size` ON `drinks`.`drink_size_id` = `drink_size`.`id`";

    private static final String FIND_ALL_ORDERS_BY_USER_ID =
            FIND_ALL_ORDERS + "WHERE `user_id`=?";

    @Override
    public List<Order> findAllOrdersByUserId(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindAllOrdersByUserIdStatement(connection, user);
             ResultSet resultSet = statement.executeQuery()) {
            List<Order> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createEntityFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while getting all orders by user id", ex);
        }
    }

    @Override
    protected PreparedStatement buildInsertStatement(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, order.getUser().getId());
        preparedStatement.setInt(++statementIndex, order.getDrink().getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildUpdateByID(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_BY_ID);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, order.getDrink().getId());
        preparedStatement.setInt(++statementIndex, order.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildDeleteById(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID);
        preparedStatement.setInt(1, order.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildFindAll(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_ORDERS);
    }

    protected PreparedStatement buildFindAllOrdersByUserIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS_BY_USER_ID);
        preparedStatement.setInt(1, user.getId());
        return preparedStatement;
    }

    @Override
    protected Order createEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new OrderBuilder()
                .setId(resultSet.getInt("id"))
                .setUser(new UserBuilder()
                        .setId(resultSet.getInt("user_id"))
                        .getResult())
                .setDrink(new DrinkBuilder()
                        .setId(resultSet.getInt("drinks_id"))
                        .setTitle(resultSet.getString("title"))
                        .setPrice(resultSet.getBigDecimal("price"))
                        .setDrinkSize(resultSet.getString("volume"))
                        .getResult())
                .getResult();
    }
}
