package by.training.khoroneko.validation;

import by.training.khoroneko.entity.Order;
import by.training.khoroneko.exception.ValidationException;

import java.util.List;

public class OrderValidation {

    public void isValidOrder(Order order) throws ValidationException {
        isNotNull(order);
        isDrinkNotNull(order);
        isValidDrinkId(order);
        isUserNotNull(order);
        isValidUserId(order);
        isValidId(order);
    }

    public void isValidOrderData(Order order) throws ValidationException {
        isNotNull(order);
        isDrinkNotNull(order);
        isValidDrinkId(order);
        isUserNotNull(order);
        isValidUserId(order);
    }

    public void isValidOrderId(Order order) throws ValidationException {
        isNotNull(order);
        isValidId(order);
    }

    public void isValidCart(List<Order> cart) throws ValidationException {
        for (Order order : cart) {
            isValidOrder(order);
        }
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

    private void isValidDrinkId(Order order) throws ValidationException {
        if (order.getDrink().getId() < 1) {
            throw new ValidationException("Invalid drink id");
        }
    }

    private void isUserNotNull(Order order) throws ValidationException {
        if (order.getUser() == null) {
            throw new ValidationException("Invalid user, user is null");
        }
    }

    private void isValidUserId(Order order) throws ValidationException {
        if (order.getUser().getId() < 1) {
            throw new ValidationException("Invalid user id");
        }
    }

    private void isValidId(Order order) throws ValidationException {
        if (!(order.getId() > 0)) {
            throw new ValidationException("Invalid order id");
        }
    }
}
