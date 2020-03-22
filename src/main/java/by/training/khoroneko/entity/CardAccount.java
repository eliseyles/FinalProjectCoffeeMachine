package by.training.khoroneko.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class CardAccount {
    private int id;
    private String cardNumber;
    private BigDecimal amount;

    public CardAccount() {
    }

    public CardAccount(int id, String cardNumber, BigDecimal amount) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAccount that = (CardAccount) o;
        return id == that.id &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, amount);
    }

    @Override
    public String toString() {
        return "CardAccount{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
