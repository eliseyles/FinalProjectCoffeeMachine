package by.training.khoroneko.builder;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.DrinkSize;

import java.math.BigDecimal;

public class DrinkBuilder {
    private int id;
    private String title;
    private BigDecimal price;
    private DrinkSize drinkSize;
    private int servingNumber;

    public DrinkBuilder() {
    }

    public DrinkBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public DrinkBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public DrinkBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public DrinkBuilder setDrinkSize(DrinkSize drinkSize) {
        this.drinkSize = drinkSize;
        return this;
    }

    public DrinkBuilder setDrinkSize(int id) {
        this.drinkSize = DrinkSize.getDrinkSizeById(id);
        return this;
    }

    public DrinkBuilder setDrinkSize(String drinkSize) {
        this.drinkSize = DrinkSize.valueOf(drinkSize);
        return this;
    }
    
    public DrinkBuilder setServingNumber(int servingNumber) {
        this.servingNumber = servingNumber;
        return this;
    }

    public Drink getResult() {
        return new Drink(id, title, price, drinkSize, servingNumber);
    }
}
