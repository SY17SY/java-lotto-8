package lotto.domain;

public enum ErrorMessage {
    PAYMENT_NEGATIVE("구입 금액은 0보다 커야 합니다."),
    PAYMENT_REMAINDER("구입 금액은 1,000원 단위이어야 합니다."),
    LOTTO_NUMBERS_BLANK("로또 번호가 비어있습니다."),
    LOTTO_NUMBERS_LENGTH("로또 번호는 6개여야 합니다."),
    LOTTO_NUMBERS_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    LOTTO_NUMBERS_DUPLICATION("로또 번호는 서로 다른 수이어야 합니다."),
    WIN_BONUS_RANGE("당첨 번호 및 보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    WIN_NUMBER_LENGTH("쉼표(,)로 구분된 6개의 당첨 번호를 작성해 주십시오."),
    WIN_NUMBER_DUPLICATION("당첨 번호는 서로 다른 수이어야 합니다."),
    WIN_BONUS_DUPLICATION("당첨 번호와 보너스 번호는 서로 다른 수이어야 합니다.");

    private static final String ERROR = "[ERROR] ";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR + message;
    }
}
