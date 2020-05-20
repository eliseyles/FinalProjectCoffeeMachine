package by.training.khoroneko.service;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ServiceException;

import java.util.List;

public interface DrinkService {

    void add(Drink drink) throws ServiceException;

    void update(Drink drink) throws ServiceException;

    void delete(Drink drink) throws ServiceException;

    List<Drink> getAll() throws ServiceException;

    Drink findById(Drink drink) throws ServiceException;

    List<Drink> getPurchaseHistoryByUserId(User user) throws ServiceException;
}
