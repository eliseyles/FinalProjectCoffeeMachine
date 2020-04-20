package by.training.khoroneko.command;

public enum Attribute {
    ERROR_MASSAGE("errorMessage"),
    USER_PROFILE("userProfile"),
    USER("user"),
    USER_LIST("userList");

    private String value;

    Attribute(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }
}
