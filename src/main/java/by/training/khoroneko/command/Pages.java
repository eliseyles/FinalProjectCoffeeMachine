package by.training.khoroneko.command;

public enum Pages {
    INDEX_JSP("page/index.jsp"),
    REGISTRATION_JSR("page/registration.jsp"),
    ERROR_JSP("page/error.jsp"),
    SIGN_IN_JSP("page/signin.jsp"),
    USER_LIST_JSP("page/userlist.jsp"),
    USER_EDIT_JSP("page/useredit.jsp"),
    DRINK_LIST_JSP("page/drinks.jsp"),
    DRINK_MANAGEMENT_JSP("page/drinkmanagement.jsp"),
    DRINK_ADDING_JSP("page/add_drink.jsp"),
    DRINK_ADDING_SERVINGS_JSP("page/add_servings.jsp");

    private String value;

    Pages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
