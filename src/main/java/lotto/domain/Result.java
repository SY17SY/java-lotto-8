package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.dto.LottosDto;
import lotto.dto.ResultDto;

public class Result {
    private final Map<Rank, Integer> rankCounts;

    Result(Map<Rank, Integer> rankCounts) {
        this.rankCounts = rankCounts;
    }

    public static Result from(LottosDto lottosDto, List<Integer> winNumbers, int bonusNumber) {
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }

        return new Result(rankCounts);
    }

    public ResultDto toDto() {
        return new ResultDto(rankCounts);
    }
}
