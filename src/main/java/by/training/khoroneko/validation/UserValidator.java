package by.training.khoroneko.validation;

import by.training.khoroneko.entity.User;
import by.training.khoroneko.exception.ValidationException;

public class UserValidator {

    public void isValidUser(User user) throws ValidationException {
        isNotNull(user);
        isValidName(user);
        isValidEmail(user);
        isValidPassword(user);
    }

    public void isValidEmailAndPassword(User user) throws ValidationException {
        isNotNull(user);
        isValidEmail(user);
        isValidPassword(user);
    }

    private void isNotNull(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("User is null");
        }
    }

    private void isValidName(User user) throws ValidationException {
        if (!user.getName().matches("^[a-zA-Z]{5,20}$")) {
            throw new ValidationException("Invalid user name");
        }
    }

    private void isValidEmail(User user) throws ValidationException {
        if (!user.getEmail().matches("^[A-Za-z0-9+_]+@(.+)$")) {
            throw new ValidationException("Invalid user email");
        }
    }

    private void isValidPassword(User user) throws ValidationException {
        if (!user.getPassword().matches("^[a-zA-Z0-9]{8,20}$")) {
            throw new ValidationException("Invalid user password");
        }
    }
}
