package by.training.khoroneko.command;

public enum JSPParameter {
    COMMAND("command"),
    REGISTER("register"),
    ERROR_MASSAGE("errorMessage"),
    USER("user"),
    USER_ID("userID"),
    USER_NAME("userName"),
    USER_EMAIL("userEmail"),
    USER_PASSWORD("userPassword"),
    USER_LIST("userList"),
    USER_PROFILE("userProfile");

    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
