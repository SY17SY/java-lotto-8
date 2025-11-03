package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.dto.LottosDto;
import lotto.dto.ResultDto;
import lotto.dto.WinAndBonusDto;

public class Result {
    private final Map<Rank, Integer> rankCounts;
    private final int profit;

    Result(Map<Rank, Integer> rankCounts) {
        this.rankCounts = rankCounts;
        this.profit = getProfit();
    }

    public static Result from(LottosDto lottosDto, WinAndBonusDto winAndBonusDto) {
        EnumMap<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }

        lottosDto.lottos().forEach(lotto -> {
            int matchCount = countMatches(lotto.numbers(), winAndBonusDto.winNumbers());
            boolean bonus = lotto.numbers().contains(winAndBonusDto.bonusNumber());
            Rank rank = Rank.fromMatches(matchCount, bonus);
            rankCounts.merge(rank, 1, Integer::sum);
        });
        return new Result(rankCounts);
    }

    private static int countMatches(List<Integer> numbers, Set<Integer> winSet) {
        return (int) numbers.stream()
                .filter(winSet::contains)
                .count();
    }

    private int getProfit() {
        return this.rankCounts.entrySet().stream()
                .mapToInt(e -> e.getValue() * e.getKey().getPrizeMoney())
                .sum();
    }

    public ResultDto toDto() {
        return new ResultDto(rankCounts, profit);
    }
}
