package by.training.khoroneko.command;

public enum Pages {
    INDEX_JSP("page/index.jsp"),
    REGISTRATION_JSR("page/registration.jsp");

    private String value;

    Pages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
