package by.training.khoroneko.builder;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.DrinkSize;

import java.math.BigDecimal;

public class DrinkBuilder {
    Drink drink;

    public DrinkBuilder(Drink drink) {
        this.drink = drink;
    }

    public void setId(int id) {
        drink.setId(id);
    }

    public void setTitle(String title) {
        drink.setTitle(title);
    }

    public void setPrice(BigDecimal price) {
        drink.setPrice(price);
    }

    public void setDrinkSize(DrinkSize drinkSize) {
        drink.setDrinkSize(drinkSize);
    }

    public void setDrinkSize(int drinkSizeId) {
        drink.setDrinkSize(DrinkSize.getDrinkSizeById(drinkSizeId));
    }

    public void setServingNumber(int servingNumber) {
        drink.setServingNumber(servingNumber);
    }
}
