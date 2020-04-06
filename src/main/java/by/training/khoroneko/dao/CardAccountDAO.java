package by.training.khoroneko.dao;

import by.training.khoroneko.entity.CardAccount;
import by.training.khoroneko.exception.DAOException;

public interface CardAccountDAO {
    int create(CardAccount newCardAccount) throws DAOException;

    void update(CardAccount newCardAccount) throws DAOException;

    void delete(CardAccount cardAccount) throws DAOException;

    CardAccount findById(int id) throws DAOException;
}
