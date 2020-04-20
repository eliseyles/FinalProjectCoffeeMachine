package by.training.khoroneko.validation;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.exception.ValidationException;

import java.math.BigDecimal;

public class DrinkValidator {

    private static final BigDecimal MIN_PRICE = new BigDecimal(0);

    public void isValidDrink(Drink drink) throws ValidationException {
        isNotNull(drink);
        isValidId(drink);
        isValidTitle(drink);
        isValidPrice(drink);
        isValidServingNumber(drink);
    }

    public void isValidDrinkId(Drink drink) throws ValidationException {
        isNotNull(drink);
        isValidId(drink);
    }

    public void isValidDrinkData(Drink drink) throws ValidationException {
        isNotNull(drink);
        isValidTitle(drink);
        isValidPrice(drink);
        isValidServingNumber(drink);
    }

    public void isValidIdAndServingNumber(Drink drink) throws ValidationException {
        isNotNull(drink);
        isValidId(drink);
        isValidServingNumber(drink);
    }

    private void isNotNull(Drink drink) throws ValidationException {
        if (drink == null) {
            throw new ValidationException("Drink is null");
        }
    }

    private void isValidTitle(Drink drink) throws ValidationException {
        if (!drink.getTitle().matches("^[a-zA-Z]{1,40}$")) {
            throw new ValidationException("Invalid drink title");
        }
    }

    private void isValidPrice(Drink drink) throws ValidationException {
        if (drink.getPrice().compareTo(MIN_PRICE) < 1) {
            throw new ValidationException("Invalid drink price");
        }
    }

    private void isValidId(Drink drink) throws ValidationException {
        if (drink.getId() < 0) {
            throw new ValidationException("Invalid drink id");
        }
    }

    private void isValidServingNumber(Drink drink) throws ValidationException {
        if (drink.getServingNumber() < 0) {
            throw new ValidationException("Invalid drink serving number");
        }
    }
}
