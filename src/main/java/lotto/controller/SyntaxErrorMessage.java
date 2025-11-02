package lotto.controller;

public enum SyntaxErrorMessage {
    PAYMENT_BLANK("구입 금액을 작성해 주십시오."),
    PAYMENT_SYNTAX("구입 금액의 형식이 올바르지 않습니다."),
    WIN_NUMBER_SYNTAX("당첨 번호의 형식이 올바르지 않습니다."),
    WIN_NUMBER_BLANK("당첨 번호를 작성해 주십시오."),
    WIN_NUMBER_DELIMITER("당첨 번호가 쉼표(,)로 구분되지 않았습니다."),
    BONUS_NUMBER_BLANK("보너스 번호를 작성해 주십시오."),
    BONUS_NUMBER_SYNTAX("보너스 번호의 형식이 올바르지 않습니다.");

    private static final String ERROR = "[ERROR] ";

    private final String message;

    SyntaxErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR + message;
    }
}
