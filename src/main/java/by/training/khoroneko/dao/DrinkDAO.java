package by.training.khoroneko.dao;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;

import java.util.List;

public interface DrinkDAO {
    Drink findById(Drink drink) throws DAOException;

    Drink findByTitleAndSizeAndPrice(Drink drink) throws DAOException;

    List<Drink> findHistoryByUserId(User user) throws DAOException;
}
