package by.training.khoroneko.dao;

import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.pool.ConnectionPool;
import by.training.khoroneko.pool.ProxyConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommonDAO<T> implements CommonDAO<T> {

    protected ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private Logger logger = Logger.getLogger(AbstractCommonDAO.class);

    @Override
    public void create(T element) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildInsertStatement(connection, element)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while adding element", ex);
        }
    }

    @Override
    public void update(T element) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildUpdateByIDStatement(connection, element)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while updating element", ex);
        }
    }

    @Override
    public void delete(T element) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildDeleteByIdStatement(connection, element)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while deleting element", ex);
        }
    }

    @Override
    public List<T> getAll() throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindAllStatement(connection);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createEntityFromResultSet(resultSet));
            }
            return result;
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while getting all elements", ex);
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                logger.error("Cannot close resultSet", ex);
            }
        }
    }

    protected void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                logger.error("Cannot close PreparedStatement", ex);
            }
        }
    }

    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                ((ProxyConnection) connection).close();
            } catch (ClassCastException ex) {
                logger.error("Invalid connection", ex);
            }
        }
    }

    protected void rollbackTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Cannot rollback transaction", ex);
            }
        }
    }

    protected abstract PreparedStatement buildInsertStatement(Connection connection, T element) throws SQLException;

    protected abstract PreparedStatement buildUpdateByIDStatement(Connection connection, T element) throws SQLException;

    protected abstract PreparedStatement buildDeleteByIdStatement(Connection connection, T element) throws SQLException;

    protected abstract PreparedStatement buildFindAllStatement(Connection connection) throws SQLException;

    protected abstract T createEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
