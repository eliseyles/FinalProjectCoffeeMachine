package by.training.khoroneko.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Drink {
    private int id;
    private String title;
    private BigDecimal price;
    private DrinkSize drinkSize;
    private int servingNumber;

    public Drink() {
    }

    public Drink(int id, String title, BigDecimal price, DrinkSize drinkSize, int servingNumber) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.drinkSize = drinkSize;
        this.servingNumber = servingNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DrinkSize getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(DrinkSize drinkSize) {
        this.drinkSize = drinkSize;
    }

    public int getServingNumber() {
        return servingNumber;
    }

    public void setServingNumber(int servingNumber) {
        this.servingNumber = servingNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return id == drink.id &&
                servingNumber == drink.servingNumber &&
                Objects.equals(title, drink.title) &&
                Objects.equals(price, drink.price) &&
                drinkSize == drink.drinkSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, drinkSize, servingNumber);
    }


    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", drinkSize=" + drinkSize +
                ", servingNumber=" + servingNumber +
                '}';
    }
}
