package by.training.khoroneko.builder;

import by.training.khoroneko.entity.CardAccount;
import by.training.khoroneko.entity.Role;
import by.training.khoroneko.entity.User;

public class UserBuilder {

    private int id;
    private String name;
    private String email;
    private String password;
    private boolean activity;
    private CardAccount cardAccount;
    private Role role;

    public UserBuilder() {
    }

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setActivity(boolean activity) {
        this.activity = activity;
        return this;
    }

    public UserBuilder setCardAccount(CardAccount cardAccount) {
        this.cardAccount = cardAccount;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder setRole(int id) {
        this.role = Role.getRoleById(id);
        return this;
    }

    public User getResult() {
        return new User(id, name, email, password, activity, cardAccount, role);
    }
}
