package lotto.view;

public enum ErrorMessage {
    PAYMENT_SYNTAX("구입 금액의 형식이 올바르지 않습니다."),
    PAYMENT_NEGATIVE("구입 금액은 0보다 커야 합니다."),
    PAYMENT_REMAINDER("구입 금액은 1,000원 단위이어야 합니다.");

    private static final String ERROR = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR + message;
    }
}
