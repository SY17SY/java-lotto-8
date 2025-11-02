package lotto.view;

public enum OutputMessage {
    LOTTOS("개를 구매했습니다."),
    PROFIT_RATE_PREFIX("총 수익률은 "),
    PROFIT_RATE_SUFFIX("%입니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
