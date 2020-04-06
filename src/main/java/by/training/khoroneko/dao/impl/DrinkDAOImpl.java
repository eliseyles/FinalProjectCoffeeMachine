package by.training.khoroneko.dao.impl;

import by.training.khoroneko.builder.CardAccountBuilder;
import by.training.khoroneko.builder.DrinkBuilder;
import by.training.khoroneko.dao.CardAccountDAO;
import by.training.khoroneko.dao.DrinkDAO;
import by.training.khoroneko.entity.CardAccount;
import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class DrinkDAOImpl implements DrinkDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private Logger logger = Logger.getLogger(DrinkDAOImpl.class);

    private static final String INSERT_DRINK =
            "INSERT INTO `drinks` (`title`, `price`, `drink_size`, `serving_number`) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_DRINK_BY_ID =
            "UPDATE `drinks` SET `title`=?, `price`=?, `drink_size`=?, `serving_number`=? WHERE id=?";

    private static final String DELETE_DRINK_BY_ID = "DELETE FROM `drinks` WHERE `id` = ?";

    private static final String FIND_DRINK_BY_ID =
            "SELECT `id`, `title`, `price`, `drink_size`, `serving_number` FROM `drinks` WHERE `id` = ?";

    @Override
    public int create(Drink newDrink) throws DAOException {
        if (newDrink == null) {
            return 0;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillInsert(connection, newDrink)) {
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while adding drink");
        }
        return 0;
    }

    @Override
    public void update(Drink newDrink) throws DAOException {
        if (newDrink == null) {
            return;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillUpdateByID(connection, newDrink)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while updating drink");
        }
    }

    @Override
    public void delete(Drink drink) throws DAOException {
        if (drink == null) {
            return;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillDeleteById(connection, drink)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while deleting drink");
        }
    }

    @Override
    public Drink findById(int id) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillFindById(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createDrinkFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while finding card account by id");
        }
    }

    private PreparedStatement fillInsert(Connection connection, Drink drink) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRINK, Statement.RETURN_GENERATED_KEYS);
        int indexTemp = 0;
        preparedStatement.setString(++indexTemp, drink.getTitle());
        preparedStatement.setBigDecimal(++indexTemp, drink.getPrice());
        preparedStatement.setInt(++indexTemp, drink.getDrinkSize().getId());
        preparedStatement.setInt(++indexTemp, drink.getServingNumber());
        return preparedStatement;
    }

    private PreparedStatement fillUpdateByID(Connection connection, Drink drink) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DRINK_BY_ID);
        int indexTemp = 0;
        preparedStatement.setString(++indexTemp, drink.getTitle());
        preparedStatement.setBigDecimal(++indexTemp, drink.getPrice());
        preparedStatement.setInt(++indexTemp, drink.getDrinkSize().getId());
        preparedStatement.setInt(++indexTemp, drink.getServingNumber());
        preparedStatement.setInt(++indexTemp, drink.getId());
        return preparedStatement;
    }

    private PreparedStatement fillDeleteById(Connection connection, Drink drink) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DRINK_BY_ID);
        int indexTemp = 0;
        preparedStatement.setInt(++indexTemp, drink.getId());
        return preparedStatement;
    }

    private PreparedStatement fillFindById(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_DRINK_BY_ID);
        int indexTemp = 0;
        preparedStatement.setInt(++indexTemp, id);
        return preparedStatement;
    }

    private Drink createDrinkFromResultSet(ResultSet resultSet) throws SQLException{
        Drink drink = new Drink();
        DrinkBuilder drinkBuilder = new DrinkBuilder(drink);
        drinkBuilder.setId(resultSet.getInt("id"));
        drinkBuilder.setTitle(resultSet.getString("title"));
        drinkBuilder.setPrice(resultSet.getBigDecimal("price"));
        drinkBuilder.setDrinkSize(resultSet.getInt("drink_size"));
        drinkBuilder.setServingNumber(resultSet.getInt("serving_number"));
        return drink;
    }
}
