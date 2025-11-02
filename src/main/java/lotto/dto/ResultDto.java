package lotto.dto;

import java.util.Map;
import lotto.domain.Rank;

public record ResultDto(Map<Rank, Integer> rankCounts, int profit) {
}
