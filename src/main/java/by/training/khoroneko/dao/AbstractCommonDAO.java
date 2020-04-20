package by.training.khoroneko.dao;

import by.training.khoroneko.exception.DAOException;
import by.training.khoroneko.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
             PreparedStatement statement = buildUpdateByID(connection, element)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while updating element", ex);
        }
    }

    @Override
    public void delete(T element) throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildDeleteById(connection, element)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new DAOException("Error while deleting element", ex);
        }
    }

    @Override
    public List<T> getAll() throws DAOException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = buildFindAll(connection);
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

    protected abstract PreparedStatement buildInsertStatement(Connection connection, T element) throws SQLException;

    protected abstract PreparedStatement buildUpdateByID(Connection connection, T element) throws SQLException;

    protected abstract PreparedStatement buildDeleteById(Connection connection, T element) throws SQLException;

    protected abstract PreparedStatement buildFindAll(Connection connection) throws SQLException;

    protected abstract T createEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
