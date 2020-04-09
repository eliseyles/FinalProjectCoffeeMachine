package by.training.khoroneko.command;

public enum JSPParameter {
    COMMAND("command"),
    FILE("file"),
    REGISTER("register");

    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
