package by.training.khoroneko.dao;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.exception.DAOException;

public interface DrinkDAO {
    Drink findById(Drink drink) throws DAOException;
}
