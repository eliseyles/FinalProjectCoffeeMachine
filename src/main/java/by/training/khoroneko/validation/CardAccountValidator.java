package by.training.khoroneko.validation;

import by.training.khoroneko.entity.CardAccount;
import by.training.khoroneko.exception.ValidationException;

import java.math.BigDecimal;


public class CardAccountValidator {

    private static final String CARD_NUMBER_REGEX = "^[0-9]{16}$";
    private static final BigDecimal MIN_AMOUNT = new BigDecimal(0);
    private static final int MIN_ID = 1;

    public void isValidCardAccountData(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidNumber(cardAccount);
    }

    public void isValidCardAccountIdAndAmount(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidId(cardAccount);
        isValidAmount(cardAccount);
    }

    public void isValidCardAccountIdAndNumber(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidId(cardAccount);
        isValidNumber(cardAccount);
    }

    public void isValidCardAccountId(CardAccount cardAccount) throws ValidationException {
        isNotNull(cardAccount);
        isValidId(cardAccount);
    }

    private void isNotNull(CardAccount cardAccount) throws ValidationException {
        if (cardAccount == null) {
            throw new ValidationException("Card account is null");
        }
    }

    private void isValidId(CardAccount cardAccount) throws ValidationException {
        if (cardAccount.getId() < MIN_ID) {
            throw new ValidationException("Card account id invalid");
        }
    }

    private void isValidNumber(CardAccount cardAccount) throws ValidationException {
        if (!cardAccount.getCardNumber().matches(CARD_NUMBER_REGEX)) {
            throw new ValidationException("Invalid card number");
        }
    }

    private void isValidAmount(CardAccount cardAccount) throws ValidationException {
        if (cardAccount.getAmount().compareTo(MIN_AMOUNT) < 1) {
            throw new ValidationException("Invalid amount");
        }
    }
}
