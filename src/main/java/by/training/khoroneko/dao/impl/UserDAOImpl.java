package by.training.khoroneko.dao.impl;

import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.dao.AbstractCommonDAO;
import by.training.khoroneko.dao.UserDAO;
import by.training.khoroneko.entity.Role;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractCommonDAO<User> implements UserDAO {

    private Logger logger = Logger.getLogger(UserDAO.class);

    private static final String INSERT_USER =
            "INSERT INTO `user` (`name`, `email`, `password`, `is_active`, `role_id`) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_BY_ID =
            "UPDATE `user` SET `name`=?, `email`=?, `password`=?, `is_active`=?, `role_id`=? WHERE id=?";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE `id` = ?";

    private static final String FIND_ALL_USERS =
            "SELECT `id`, `name`, `email`, `password`, `is_active`, `card_account_id`, `role_id` FROM `user`";

    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT `user`.`id`, `name`, `email`, `password`, `is_active`, `card_account_id`, `title` as `role_title` " +
                    "FROM `user` join `role` on `user`.`role_id` = `role`.`id`" +
                    "WHERE `email`=? AND `password`=?";

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
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while getting user by email and password");
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
        preparedStatement.setString(++statementIndex, user.getName());
        preparedStatement.setString(++statementIndex, user.getEmail());
        preparedStatement.setString(++statementIndex, user.getPassword());
        preparedStatement.setBoolean(++statementIndex, user.isActivity());
        preparedStatement.setInt(++statementIndex, user.getRole().getId());
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

    @Override
    protected User createEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setActivity(resultSet.getBoolean("is_active"))
                .setRole(Role.valueOf(resultSet.getString("role")))
                .getResult();
    }
}
