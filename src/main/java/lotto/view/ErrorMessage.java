package lotto.view;

public enum ErrorMessage {
    PAYMENT_BLANK("구입 금액을 작성해 주십시오."),
    PAYMENT_SYNTAX("구입 금액의 형식이 올바르지 않습니다."),
    PAYMENT_NEGATIVE("구입 금액은 0보다 커야 합니다."),
    PAYMENT_REMAINDER("구입 금액은 1,000원 단위이어야 합니다."),
    LOTTO_NUMBERS_LENGTH("로또 번호는 6개여야 합니다."),
    LOTTO_NUMBERS_DUPLICATION("로또 번호는 서로 다른 수이어야 합니다."),
    WIN_NUMBER_BLANK("당첨 번호를 작성해 주십시오."),
    WIN_NUMBER_DELIMITER("당첨 번호가 쉼표(,)로 구분되지 않았습니다."),
    WIN_NUMBER_SYNTAX("당첨 번호의 형식이 올바르지 않습니다."),
    BONUS_NUMBER_BLANK("보너스 번호를 작성해 주십시오."),
    BONUS_NUMBER_SYNTAX("보너스 번호의 형식이 올바르지 않습니다.");

    private static final String ERROR = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR + message;
    }
}
