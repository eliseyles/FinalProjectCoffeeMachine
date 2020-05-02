package by.training.khoroneko.validation;

import by.training.khoroneko.entity.CardAccount;
import by.training.khoroneko.exception.ValidationException;


public class CardAccountValidator {

    public void isValidCardAccount(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidNumber(cardAccount);
    }

    private void isNotNull(CardAccount cardAccount) throws ValidationException{
        if (cardAccount == null) {
            throw new ValidationException("Card account is null");
        }
    }

    private void isValidId(CardAccount cardAccount) throws ValidationException{
        if (cardAccount.getId() <= 0) {
            throw new ValidationException("Card account id invalid");
        }
    }

    private void isValidNumber(CardAccount cardAccount) throws ValidationException{
        if (!cardAccount.getCardNumber().matches("^[0-9]{16}$")) {
            throw new ValidationException("Invalid card number");
        }
    }
}
