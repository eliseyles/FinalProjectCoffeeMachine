package by.training.khoroneko.dao;

import by.training.khoroneko.exception.DAOException;

import java.util.List;

public interface CommonDAO<T> {
    void create(T element) throws DAOException;

    void update(T element) throws DAOException;

    void delete(T element) throws DAOException;

    List<T> getAll() throws DAOException;
}
