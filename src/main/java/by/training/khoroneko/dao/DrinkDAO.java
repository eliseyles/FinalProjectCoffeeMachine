package by.training.khoroneko.dao;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.DAOException;

import java.util.List;

/**
 * Drink dao interface.
 */
public interface DrinkDAO {
    /**
     * Find drink by id.
     *
     * @param drink entity with id
     * @return finding drink
     * @throws DAOException dao exception.
     */
    Drink findById(Drink drink) throws DAOException;

    /**
     * Find drink by title, price and size.
     *
     * @param drink entity with finding title, price, size.
     * @return finded drink
     * @throws DAOException dao exception.
     */
    Drink findByTitleAndSizeAndPrice(Drink drink) throws DAOException;

    /**
     * Find drinks that user bought by user id.
     *
     * @param user entity with id
     * @return finded drink list
     * @throws DAOException dao exception.
     */
    List<Drink> findHistoryByUserId(User user) throws DAOException;
}
