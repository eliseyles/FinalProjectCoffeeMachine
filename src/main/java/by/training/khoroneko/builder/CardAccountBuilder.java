package by.training.khoroneko.builder;

import by.training.khoroneko.entity.CardAccount;

import java.math.BigDecimal;

public class CardAccountBuilder {
    private CardAccount cardAccount;

    public CardAccountBuilder(CardAccount cardAccount) {
        this.cardAccount = cardAccount;
    }

    public void setId(int id) {
        cardAccount.setId(id);
    }

    public void setCardNumber(String number) {
        cardAccount.setCardNumber(number);
    }

    public void setAmount(BigDecimal amount) {
        cardAccount.setAmount(amount);
    }
}
