package by.training.khoroneko.command;

public enum Pages {
    INDEX_JSP("page/index.jsp"),
    REGISTRATION_JSR("page/registration.jsp"),
    ERROR_JSP("page/error.jsp"),
    SIGN_IN_JSP("page/sign_in.jsp"),
    USER_LIST_JSP("page/user_list.jsp"),
    USER_EDIT_JSP("page/user_edit.jsp"),
    DRINK_LIST_JSP("page/drinks.jsp"),
    DRINK_MANAGEMENT_JSP("page/drink_management.jsp"),
    DRINK_ADDING_JSP("page/add_drink.jsp"),
    DRINK_ADDING_SERVINGS_JSP("page/add_servings.jsp"),
    USER_PROFILE_JSP("page/user_profile.jsp"),
    EDIT_PROFILE_JSP("page/edit_profile.jsp"),
    ADD_CARD_JSP("page/add_card.jsp"),
    EDIT_CARD_JSP("page/edit_card.jsp"),
    ADD_MONEY_JSP("page/add_money.jsp"),
    CART_JSP("page/cart.jsp");

    private String value;

    Pages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
