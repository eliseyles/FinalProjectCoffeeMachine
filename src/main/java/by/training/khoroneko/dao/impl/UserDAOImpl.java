package by.training.khoroneko.dao.impl;

import by.training.khoroneko.builder.UserBuilder;
import by.training.khoroneko.dao.UserDAO;
import by.training.khoroneko.entity.Role;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private Logger logger = Logger.getLogger(UserDAOImpl.class);

    private static final String INSERT_USER =
            "INSERT INTO `user` (`name`, `email`, `password`, `is_active`, `role_id`) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_BY_ID =
            "UPDATE `user` SET `name`=?, `email`=?, `password`=?, `is_active`=?, `role_id`=? WHERE id=?";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE `id` = ?";

    private static final String FIND_USER_BY_ID =
            "SELECT `id`, `name`, `email`, `password`, `is_active`, `role_id` FROM `user` WHERE `id` = ?";

    private static final String FIND_USER_BY_EMAIL =
            "SELECT `id`, `name`, `email`, `password`, `is_active`, `role_id` FROM `user` WHERE `email` = ?";


    @Override
    public int create(User newUser) throws DAOException {
        if (newUser == null) {
            return 0;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillInsert(connection, newUser)) {
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while adding user");
        }
        return 0;
    }

    @Override
    public void update(User newUser) throws DAOException {
        if (newUser == null) {
            return;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillUpdateByID(connection, newUser)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while updating user");
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        if (user == null) {
            return;
        }
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillDeleteById(connection, user)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while deleting user");
        }
    }

    @Override
    public User findById(int id) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillFindById(connection, id);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createUserFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while finding user by id");
        }
    }

    @Override
    public User findByEmail(String email) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = fillFindByEmail(connection, email);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return createUserFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error while finding user by email");
        }
    }

    private PreparedStatement fillInsert(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        int indexTemp = 0;
        preparedStatement.setString(++indexTemp, user.getName());
        preparedStatement.setString(++indexTemp, user.getEmail());
        preparedStatement.setString(++indexTemp, user.getPassword());
        preparedStatement.setBoolean(++indexTemp, user.isActivity());
        preparedStatement.setInt(++indexTemp, user.getRole().getId());
        return preparedStatement;
    }

    private PreparedStatement fillUpdateByID(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID);
        int indexTemp = 0;
        preparedStatement.setString(++indexTemp, user.getName());
        preparedStatement.setString(++indexTemp, user.getEmail());
        preparedStatement.setString(++indexTemp, user.getPassword());
        preparedStatement.setBoolean(++indexTemp, user.isActivity());
        preparedStatement.setInt(++indexTemp, user.getRole().getId());
        preparedStatement.setInt(++indexTemp, user.getId());
        return preparedStatement;
    }

    private PreparedStatement fillDeleteById(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
        int indexTemp = 0;
        preparedStatement.setInt(++indexTemp, user.getId());
        return preparedStatement;
    }

    private PreparedStatement fillFindById(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
        int indexTemp = 0;
        preparedStatement.setInt(++indexTemp, id);
        return preparedStatement;
    }

    private PreparedStatement fillFindByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
        int indexTemp = 0;
        preparedStatement.setString(++indexTemp, email);
        return preparedStatement;
    }

    private User createUserFromResultSet(final ResultSet resultSet) throws SQLException {
        User user = new User();
        UserBuilder userBuilder = new UserBuilder(user);
        userBuilder.setId(resultSet.getInt("id"));
        userBuilder.setName(resultSet.getString("name"));
        userBuilder.setEmail(resultSet.getString("email"));
        userBuilder.setPassword(resultSet.getString("password"));
        userBuilder.setActivity(resultSet.getBoolean("is_active"));
        userBuilder.setRole(resultSet.getInt("role_id"));
        return user;
    }
}
