package by.training.khoroneko.dao;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.exception.DAOException;

public interface DrinkDAO {
    int create(Drink newDrink) throws DAOException;

    void update(Drink newDrink) throws DAOException;

    void delete(Drink drink) throws DAOException;

    Drink findById(int id) throws DAOException;
}
