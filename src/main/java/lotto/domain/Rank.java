package lotto.domain;

import java.util.Map;

public enum Rank {
    FIRST("6개 일치", 2000000000),
    SECOND("5개 일치, 보너스 볼 일치", 30000000),
    THIRD("5개 일치", 1500000),
    FOURTH("4개 일치", 50000),
    FIFTH("3개 일치", 5000),
    MISS("", 0);

    private static final Map<Integer, Rank> NON_BONUS_MAP = Map.of(
            6, FIRST,
            4, FOURTH,
            3, FIFTH
    );

    private static final Map<Boolean, Rank> BONUS_MAP = Map.of(
            true, SECOND,
            false, THIRD
    );

    private final String promptHead;
    private final int prizeMoney;

    Rank(String promptHead, int prizeMoney) {
        this.promptHead = promptHead;
        this.prizeMoney = prizeMoney;
    }

    public static Rank fromMatches(int matches, boolean bonus) {
        if (matches == 5) {
            return BONUS_MAP.get(bonus);
        }
        return NON_BONUS_MAP.getOrDefault(matches, MISS);
    }

    public String getPrompt() {
        return promptHead + String.format(" (%,d원) -", prizeMoney);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
