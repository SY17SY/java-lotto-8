package lotto.domain;

public enum Rank {
    FIRST("6개 일치", 2000000000),
    SECOND("5개 일치, 보너스 볼 일치", 30000000),
    THIRD("5개 일치", 1500000),
    FOURTH("4개 일치", 50000),
    FIFTH("3개 일치", 5000);

    private static final String PREFIX = "(";
    private static final String SUFFIX = ") - ";

    private final String promptHead;
    private final int prizeMoney;

    Rank(String promptHead, int prizeMoney) {
        this.promptHead = promptHead;
        this.prizeMoney = prizeMoney;
    }

    public String getPrompt() {
        return promptHead + PREFIX + prizeMoney + SUFFIX;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
