package by.training.khoroneko.builder;

import by.training.khoroneko.entity.CardAccount;
import by.training.khoroneko.entity.Role;
import by.training.khoroneko.entity.User;

public class UserBuilder {
    User user;

    public UserBuilder(User user) {
        this.user = user;
    }

    public void setId(int id) {
        user.setId(id);
    }

    public void setName(String name) {
        user.setName(name);
    }

    public void setEmail(String email) {
        user.setEmail(email);
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    public void setActivity(boolean activity) {
        user.setActivity(activity);
    }

    public void setCardAccount(CardAccount cardAccount) {
        user.setCardAccount(cardAccount);
    }

    public void setRole(Role role) {
        user.setRole(role);
    }

    public void setRole(int id) {
        user.setRole(Role.getRoleById(id));
    }
}
