package by.training.khoroneko.validation;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ValidationException;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseValidator {

    private static final BigDecimal MIN_CARD_AMOUNT = new BigDecimal(0);

    public void isValidPurchase(List<Order> cart, List<Drink> drinks, User user) throws ValidationException {
        isValidCardAccount(user);
        isEnoughUserMoneyAmount(user, getCartAmount(cart));
        isEnoughServingNumber(subtractCartOrdersFromServingNumbers(cart, drinks));
    }

    private void isEnoughUserMoneyAmount(User user, BigDecimal cartAmount) throws ValidationException {
        if (user.getCardAccount().getAmount().subtract(cartAmount).compareTo(MIN_CARD_AMOUNT) < 0) {
            throw new ValidationException("Not enough money to get purchase");
        }
    }

    private BigDecimal getCartAmount(List<Order> cart) {
        BigDecimal amount = new BigDecimal(0);
        for (Order order : cart) {
            amount = amount.add(order.getDrink().getPrice());
        }
        return amount;
    }

    private void isEnoughServingNumber(List<Drink> drinks) throws ValidationException {
        for (Drink drink : drinks) {
            if (drink.getServingNumber() < 0) {
                throw new ValidationException("Not enough serving number of" + drink.getId() + " to get purchase");
            }
        }
    }

    private List<Drink> subtractCartOrdersFromServingNumbers(List<Order> cart, List<Drink> drinks) {
        for (Order order : cart) {
            for (Drink drink : drinks) {
                if (order.getDrink().getId() == drink.getId()) {
                    drink.setServingNumber(drink.getServingNumber() - 1);
                    break;
                }
            }
        }
        return drinks;
    }

    private void isValidCardAccount(User user) throws ValidationException {
        if (user.getCardAccount().getCardNumber() == null) {
            throw new ValidationException("No attached card");
        }
    }
}
