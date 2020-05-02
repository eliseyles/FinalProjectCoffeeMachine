package by.training.khoroneko.command;

public enum JSPParameter {
    COMMAND("command"),
    REGISTER("register"),
    USER_ID("userID"),
    USER_NAME("userName"),
    USER_EMAIL("userEmail"),
    USER_PASSWORD("userPassword"),
    DRINK_TITLE("drinkTitle"),
    DRINK_VOLUME("drinkVolume"),
    DRINK_PRICE("drinkPrice"),
    DRINK_SERVING_NUMBER("servingNumber"),
    DRINK_ID("drinkId"),
    CARD_ID("cardId"),
    CARD_NUMBER("cardNumber"),
    CARD_AMOUNT("cardAmount");

    private String value;

    JSPParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
