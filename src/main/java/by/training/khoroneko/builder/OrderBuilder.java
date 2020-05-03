package by.training.khoroneko.builder;

import by.training.khoroneko.entity.Drink;
import by.training.khoroneko.entity.Order;
import by.training.khoroneko.entity.User;

public class OrderBuilder {
    private int id;
    private User user;
    private Drink drink;

    public OrderBuilder() {
    }

    public OrderBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public OrderBuilder setDrink(Drink drink) {
        this.drink = drink;
        return this;
    }

    public Order getResult() {
        return new Order(id, user, drink);
    }
}
