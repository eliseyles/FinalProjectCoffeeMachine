package by.training.khoroneko.dao.impl;

import by.training.khoroneko.builder.DrinkBuilder;
import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.DrinkDAO;
import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrinkDAOImpl extends AbstractCommonDAO<Drink> implements DrinkDAO {

    private Logger logger = Logger.getLogger(DrinkDAOImpl.class);

    private static final String INSERT_DRINK =
            "INSERT INTO `drinks` (`title`, `price`, `drink_size_id`, `serving_number`) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_DRINK_BY_ID =
            "UPDATE `drinks` SET `serving_number`=? WHERE id=?";

    private static final String DELETE_DRINK_BY_ID = "DELETE FROM `drinks` WHERE `id` = ?";

    private static final String FIND_ALL_DRINKS =
            "SELECT `drinks`.`id`, `title`, `price`, `serving_number`, `volume`" +
                    "FROM `drinks` JOIN `drink_size` ON `drinks`.`drink_size_id` = `drink_size`.`id`";

    private static final String FIND_DRINK_BY_ID =
            FIND_ALL_DRINKS + "WHERE `drinks`.`id`=?";

    private static final String FIND_DRINK_BY_TITLE_AND_SIZE_AND_PRICE =
            FIND_ALL_DRINKS + "WHERE `title`=? AND `volume`=? AND `price`=?";

    @Override
    public Drink findById(Drink drink) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindByIdStatement(connection, drink);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createEntityFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    public Drink findByTitleAndSizeAndPrice(Drink drink) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindByTitleAndSizeAndPriceStatement(connection, drink);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createEntityFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException(ex);
        }
    }

    @Override
    protected PreparedStatement buildInsertStatement(Connection connection, Drink drink) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRINK);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, drink.getTitle());
        preparedStatement.setBigDecimal(++statementIndex, drink.getPrice());
        preparedStatement.setInt(++statementIndex, drink.getDrinkSize().getId());
        preparedStatement.setInt(++statementIndex, drink.getServingNumber());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildUpdateByIDStatement(Connection connection, Drink drink) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DRINK_BY_ID);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, drink.getServingNumber());
        preparedStatement.setInt(++statementIndex, drink.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildDeleteByIdStatement(Connection connection, Drink drink) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DRINK_BY_ID);
        preparedStatement.setInt(1, drink.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildFindAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_DRINKS);
    }

    protected PreparedStatement buildFindByIdStatement(Connection connection, Drink drink) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_DRINK_BY_ID);
        preparedStatement.setInt(1, drink.getId());
        return preparedStatement;
    }

    protected PreparedStatement buildFindByTitleAndSizeAndPriceStatement(Connection connection, Drink drink) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_DRINK_BY_TITLE_AND_SIZE_AND_PRICE);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, drink.getTitle());
        preparedStatement.setString(++statementIndex, drink.getDrinkSize().toString());
        preparedStatement.setBigDecimal(++statementIndex, drink.getPrice());
        return preparedStatement;
    }

    @Override
    protected Drink createEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new DrinkBuilder()
                .setId(resultSet.getInt("id"))
                .setTitle(resultSet.getString("title"))
                .setPrice(resultSet.getBigDecimal("price"))
                .setServingNumber(resultSet.getInt("serving_number"))
                .setDrinkSize(resultSet.getString("volume"))
                .getResult();
    }


}
