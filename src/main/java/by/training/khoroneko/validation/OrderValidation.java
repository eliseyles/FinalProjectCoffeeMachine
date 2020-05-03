package by.training.khoroneko.validation;

import by.training.khoroneko.entity.Order;
import by.training.khoroneko.exception.ValidationException;

public class OrderValidation {

    public void isValidOrder(Order order) throws ValidationException {
        isNotNull(order);
        isDrinkNotNull(order);
        isUserNotNull(order);
        isValidId(order);
    }

    public void isValidOrderData(Order order) throws ValidationException {
        isNotNull(order);
        isDrinkNotNull(order);
        isUserNotNull(order);
    }

    private void isNotNull(Order order) throws ValidationException {
        if (order == null) {
            throw new ValidationException("Invalid order, order is null");
        }
    }

    private void isDrinkNotNull(Order order) throws ValidationException {
        if (order.getDrink() == null) {
            throw new ValidationException("Invalid drink, drink is null");
        }
    }

    private void isUserNotNull(Order order) throws ValidationException {
        if (order.getUser() == null) {
            throw new ValidationException("Invalid user, user is null");
        }
    }

    private void isValidId(Order order) throws ValidationException {
        if (order.getId() > 0) {
            throw new ValidationException("Invalid order id");
        }
    }
}
