package by.training.khoroneko.pool;

import by.training.khoroneko.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ConnectionPool {
    INSTANCE;

    private Logger logger = Logger.getLogger(ConnectionPool.class);
    private BlockingQueue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> usedConnections;

    private static final int POOL_SIZE = 16;

    private AtomicBoolean isInitialized = new AtomicBoolean(false);

    private static final String DATABASE_PROPERTIES_FILE = "database.properties";
    private static final String DATABASE_URL = "url";
    private static final String DATABASE_DRIVER = "driver";

    ConnectionPool() {
        availableConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        usedConnections = new ArrayDeque<>();
    }

    public void init() throws ConnectionPoolException {
        if (!isInitialized.get()) {
            Properties properties = new Properties();
            ClassLoader classLoader = this.getClass().getClassLoader();
            try {
                properties.load(classLoader.getResourceAsStream(DATABASE_PROPERTIES_FILE));
                String url = properties.getProperty(DATABASE_URL);
                String driver = properties.getProperty(DATABASE_DRIVER);
                Class.forName(driver);
                for (int i = 0; i < POOL_SIZE; i++) {
                    availableConnections.add(new ProxyConnection(DriverManager.getConnection(url,properties)));
                }
                isInitialized.set(true);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                logger.fatal(e);
                throw new ConnectionPoolException("Failed to initialize pool");
            }
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public Connection getConnection(long waitingTimeInSecond) {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.poll(waitingTimeInSecond, TimeUnit.SECONDS);
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (connection.getClass() != ProxyConnection.class) {
            throw new ConnectionPoolException();
        }
        usedConnections.remove(connection);
        availableConnections.offer((ProxyConnection) connection);
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                availableConnections.take().realClose();
            } catch (SQLException | InterruptedException e) {
                logger.error(e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
    }

}
