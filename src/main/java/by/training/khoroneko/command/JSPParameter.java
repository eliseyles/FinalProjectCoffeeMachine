package by.training.khoroneko.command;

public enum JSPParameter {
    COMMAND("command"),
    REGISTER("register"),
    USER_ID("userID"),
    USER_NAME("userName"),
    USER_EMAIL("userEmail"),
    USER_PASSWORD("userPassword");

    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
