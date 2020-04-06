package by.training.khoroneko.dao.impl;

import by.training.khoroneko.builder.CardAccountBuilder;
import by.training.khoroneko.dao.CardAccountDAO;
import by.training.khoroneko.entity.CardAccount;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class CardAccountDAOImpl implements CardAccountDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private Logger logger = Logger.getLogger(CardAccountDAO.class);

    private static final String INSERT_CARD_ACCOUNT =
            "INSERT INTO `card_account` (`number`, `amount`) VALUES (?, ?)";

    private static final String UPDATE_CARD_ACCOUNT_BY_ID =
            "UPDATE `card_account` SET `number`=?, `amount`=? WHERE id=?";

    private static final String DELETE_CARD_ACCOUNT_BY_ID = "DELETE FROM `card_account` WHERE `id` = ?";

    private static final String FIND_CARD_ACCOUNT_BY_ID =
            "SELECT `id`, `number`, `amount` FROM `card_account` WHERE `id` = ?";


    @Override
    public int create(CardAccount newCardAccount) throws DAOException {
        if (newCardAccount == null) {
            return 0;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillInsert(connection, newCardAccount)) {
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while adding card account");
        }
        return 0;
    }

    @Override
    public void update(CardAccount newCardAccount) throws DAOException {
        if (newCardAccount == null) {
            return;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillUpdateByID(connection, newCardAccount)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while updating card account");
        }
    }

    @Override
    public void delete(CardAccount cardAccount) throws DAOException {
        if (cardAccount == null) {
            return;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillDeleteById(connection, cardAccount)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while deleting card account");
        }
    }

    @Override
    public CardAccount findById(int id) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillFindById(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createCardAccountFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while finding card account by id");
        }
    }

    private PreparedStatement fillInsert(Connection connection, CardAccount cardAccount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARD_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
        int indexTemp = 0;
        preparedStatement.setString(++indexTemp, cardAccount.getCardNumber());
        preparedStatement.setBigDecimal(++indexTemp, cardAccount.getAmount());
        return preparedStatement;
    }

    private PreparedStatement fillUpdateByID(Connection connection, CardAccount cardAccount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARD_ACCOUNT_BY_ID);
        int indexTemp = 0;
        preparedStatement.setString(++indexTemp, cardAccount.getCardNumber());
        preparedStatement.setBigDecimal(++indexTemp, cardAccount.getAmount());
        return preparedStatement;
    }

    private PreparedStatement fillDeleteById(Connection connection, CardAccount cardAccount) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARD_ACCOUNT_BY_ID);
        int indexTemp = 0;
        preparedStatement.setInt(++indexTemp, cardAccount.getId());
        return preparedStatement;
    }

    private PreparedStatement fillFindById(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARD_ACCOUNT_BY_ID);
        int indexTemp = 0;
        preparedStatement.setInt(++indexTemp, id);
        return preparedStatement;
    }

    private CardAccount createCardAccountFromResultSet(ResultSet resultSet) throws SQLException{
        CardAccount cardAccount = new CardAccount();
        CardAccountBuilder cardAccountBuilder = new CardAccountBuilder(cardAccount);
        cardAccountBuilder.setId(resultSet.getInt("id"));
        cardAccountBuilder.setCardNumber(resultSet.getString("number"));
        cardAccountBuilder.setAmount(resultSet.getBigDecimal("amount"));
        return cardAccount;
    }
}
