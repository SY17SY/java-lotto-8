package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import lotto.dto.LottosDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @DisplayName("성공: Result 생성")
    @Test
    void Result_생성() {
        LottosDto lottosDto = new Lottos(3, List.of(
                Lotto.of(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.of(List.of(1, 2, 3, 8, 9, 10)),
                Lotto.of(List.of(1, 2, 3, 4, 5, 7))
        )).toDto();
        List<Integer> winNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        Result result = Result.from(lottosDto, WinAndBonus.of(winNumbers, bonusNumber).toDto());
        Map<Rank, Integer> rankCounts = result.toDto().rankCounts();

        assertThat(rankCounts.get(Rank.FIRST)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.SECOND)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.THIRD)).isEqualTo(0);
        assertThat(rankCounts.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(rankCounts.get(Rank.FIFTH)).isEqualTo(1);
    }
}
