package lotto.domain;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.dto.LottoDto;
import lotto.dto.LottosDto;
import lotto.dto.ResultDto;

public class Result {
    private static final int NUMBER_LENGTH = 6;
    private static final int NUMBER_START = 1;
    private static final int NUMBER_END = 45;

    private final Map<Rank, Integer> rankCounts;

    Result(Map<Rank, Integer> rankCounts) {
        this.rankCounts = rankCounts;
    }

    public static Result from(LottosDto lottosDto, List<Integer> winNumbers, int bonusNumber) {
        Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);
        Set<Integer> winSet = new HashSet<>(winNumbers);

        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }

        for (LottoDto lottoDto : lottosDto.lottos()) {
            int matchCount = countMatches(lottoDto.numbers(), winSet);
            boolean bonus = lottoDto.numbers().contains(bonusNumber);
            Rank rank = Rank.fromMatches(matchCount, bonus);
            if (rank != Rank.MISS) {
                rankCounts.put(rank, rankCounts.get(rank) + 1);
            }
        }
        return new Result(rankCounts);
    }

    private static int countMatches(List<Integer> numbers, Set<Integer> winSet) {
        int count = 0;
        for (Integer n : numbers) {
            if (winSet.contains(n)) {
                count++;
            }
        }
        return count;
    }

    public ResultDto toDto() {
        return new ResultDto(rankCounts);
    }
}
