package by.training.khoroneko.dao.impl;

import by.training.khoroneko.builder.CardAccountBuilder;
import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.UserDAO;
import by.training.khoroneko.entity.CardAccount;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ConnectionPoolException;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.factory.DAOFactory;
import by.training.khoroneko.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;

public class UserDAOImpl extends AbstractCommonDAO<User> implements UserDAO {

    private Logger logger = Logger.getLogger(UserDAOImpl.class);

    private static final String INSERT_USER =
            "INSERT INTO `user` (`name`, `email`, `password`, `is_active`, `role_id`) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_BY_ID =
            "UPDATE `user` SET `is_active`=? WHERE id=?";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE `id` = ?";

    private static final String FIND_ALL_USERS =
            "SELECT `user`.`id`, `name`, `email`, `password`, `is_active`," +
                    " `card_account`.`id` as `card_id`, `number` as `card_number`, `amount` as `card_amount`," +
                    " `title` as `role_title` " +
                    "FROM `user` join `role` on `user`.`role_id` = `role`.`id`" +
                    "left join `card_account` on `user`.`card_account_id` = `card_account`.`id`";

    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            FIND_ALL_USERS + "WHERE `email`=? AND `password`=?";

    private static final String FIND_USER_BY_EMAIL =
            FIND_ALL_USERS + "WHERE `email`=?";

    private static final String FIND_USER_BY_ID =
            FIND_ALL_USERS + "WHERE `user`.`id`=?";

    private static final String UPDATE_USER_INFO_BY_ID =
            "UPDATE `user` SET `name`=?, `email`=?, `password`=? WHERE id=?";

    private static final String INSERT_CARD =
            "INSERT INTO `card_account` (`number`, `amount`) VALUES (?, ?)";

    private static final String UPDATE_USER_CARD_BY_ID =
            "UPDATE `user` SET `card_account_id`=? WHERE id=?";

    @Override
    public User findByEmailAndPassword(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindByEmailAndPassword(connection, user);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createEntityFromResultSet(resultSet);
            }
//            todo change to return optional
            return null;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while getting user by email and password", ex);
        }
    }

    @Override
    public User findByEmail(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindByEmail(connection, user);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createEntityFromResultSet(resultSet);
            }
//            todo change to return optional
            return null;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while getting user by email", ex);
        }
    }

    @Override
    public User findById(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindById(connection, user);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createEntityFromResultSet(resultSet);
            }
//            todo change to return optional
            return null;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while getting user by id", ex);
        }
    }

    @Override
    public void updateUserInfoById(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildUpdateUserInfoById(connection, user)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while updating user info", ex);
        }
    }

    @Override
    public int addCard(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildAddCardStatement(connection, user)) {
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while adding card", ex);
        }
        return 0;
    }

    @Override
    public void attachCardToUserById(User user) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildUpdateUserCardByIdStatement(connection, user)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while updating user card", ex);
        }
    }

    @Override
    protected PreparedStatement buildInsertStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getName());
        preparedStatement.setString(++statementIndex, user.getEmail());
        preparedStatement.setString(++statementIndex, user.getPassword());
        preparedStatement.setBoolean(++statementIndex, user.isActivity());
        preparedStatement.setInt(++statementIndex, user.getRole().getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildUpdateByID(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID);
        int statementIndex = 0;
        preparedStatement.setBoolean(++statementIndex, user.isActivity());
        preparedStatement.setInt(++statementIndex, user.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildDeleteById(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
        preparedStatement.setInt(1, user.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement buildFindAll(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_USERS);
    }

    protected PreparedStatement buildFindByEmailAndPassword(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getEmail());
        preparedStatement.setString(++statementIndex, user.getPassword());
        return preparedStatement;
    }

    protected PreparedStatement buildFindByEmail(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getEmail());
        return preparedStatement;
    }

    protected PreparedStatement buildFindById(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, user.getId());
        return preparedStatement;
    }

    protected PreparedStatement buildUpdateUserInfoById(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_INFO_BY_ID);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getName());
        preparedStatement.setString(++statementIndex, user.getEmail());
        preparedStatement.setString(++statementIndex, user.getPassword());
        preparedStatement.setInt(++statementIndex, user.getId());
        return preparedStatement;
    }

    protected PreparedStatement buildAddCardStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARD, Statement.RETURN_GENERATED_KEYS);
        int statementIndex = 0;
        preparedStatement.setString(++statementIndex, user.getCardAccount().getCardNumber());
        preparedStatement.setBigDecimal(++statementIndex, user.getCardAccount().getAmount());
        return preparedStatement;
    }

    protected PreparedStatement buildUpdateUserCardByIdStatement(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_CARD_BY_ID);
        int statementIndex = 0;
        preparedStatement.setInt(++statementIndex, user.getCardAccount().getId());
        preparedStatement.setInt(++statementIndex, user.getId());
        return preparedStatement;
    }

    @Override
    protected User createEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setActivity(resultSet.getBoolean("is_active"))
                .setCardAccount(new CardAccountBuilder()
                        .setId(resultSet.getInt("card_id"))
                        .setCardNumber(resultSet.getString("card_number"))
                        .setAmount(resultSet.getBigDecimal("card_amount"))
                        .getResult())
                .setRole(resultSet.getString("role_title"))
                .getResult();
    }
}
