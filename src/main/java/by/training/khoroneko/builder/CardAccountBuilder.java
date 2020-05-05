package by.training.khoroneko.builder;

import by.training.khoroneko.entity.CardAccount;

import java.math.BigDecimal;

public class CardAccountBuilder {
    private int id;
    private String cardNumber;
    private BigDecimal amount;

    public CardAccountBuilder() {
    }

    public CardAccountBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public CardAccountBuilder setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public CardAccountBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public CardAccount getResult() {
        return new CardAccount(id, cardNumber, amount);
    }
}
