package by.training.khoroneko.command;

public enum Pages {
    INDEX_JSP("page/index.jsp"),
    REGISTRATION_JSR("page/registration.jsp"),
    ERROR_JSP("page/error.jsp"),
    SIGN_IN_JSP("page/sign_in.jsp"),
    DRINK_LIST_JSP("page/drinks.jsp"),

    USER_LIST_JSP("page/admin/user_list.jsp"),
    USER_EDIT_JSP("page/admin/user_edit.jsp"),
    DRINK_MANAGEMENT_JSP("page/admin/drink_management.jsp"),
    DRINK_ADDING_JSP("page/admin/add_drink.jsp"),
    DRINK_ADDING_SERVINGS_JSP("page/admin/add_servings.jsp"),

    USER_PROFILE_JSP("page/user/user_profile.jsp"),
    EDIT_PROFILE_JSP("page/user/edit_profile.jsp"),
    ADD_CARD_JSP("page/user/add_card.jsp"),
    EDIT_CARD_JSP("page/user/edit_card.jsp"),
    ADD_MONEY_JSP("page/user/add_money.jsp"),
    CART_JSP("page/user/cart.jsp");

    private String value;

    Pages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
