package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;
import lotto.dto.LottoDto;
import lotto.view.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    @DisplayName("실패: 당첨 번호 중 1~45 범위를 이탈하는 숫자가 있으면 예외 발생")
    @Test
    void 당첨_번호에_1_45_범위_외의_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Result.from(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)).toDto()), List.of(1, 2, 3, 4, 5, 46), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_BONUS_RANGE.getMessage());
    }

    @DisplayName("실패: 보너스 번호가 1~45 범위를 이탈하면 예외 발생")
    @Test
    void 보너스_번호가_1_45_범위_외이면_예외가_발생한다() {
        assertThatThrownBy(() -> Result.from(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)).toDto()), List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_BONUS_RANGE.getMessage());
    }

    @DisplayName("실패: 당첨 번호가 6개보다 적으면 예외 발생")
    @Test
    void 당첨_번호가_6개보다_적으면_예외가_발생한다() {
        assertThatThrownBy(() -> Result.from(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)).toDto()), List.of(1, 2, 3, 4, 5), 45))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_NUMBER_LENGTH.getMessage());
    }

    @DisplayName("실패: 당첨 번호가 6개보다 많으면 예외 발생")
    @Test
    void 당첨_번호가_6개보다_많으면_예외가_발생한다() {
        assertThatThrownBy(() -> Result.from(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)).toDto()), List.of(1, 2, 3, 4, 5, 6, 7), 45))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_NUMBER_LENGTH.getMessage());
    }

    @DisplayName("실패: 당첨 번호 중 중복되는 숫자가 있으면 예외 발생")
    @Test
    void 당첨_번호_중_중복_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Result.from(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)).toDto()), List.of(1, 2, 3, 4, 5, 5), 45))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_NUMBER_DUPLICATION.getMessage());
    }

    @DisplayName("실패: 당첨 번호와 보너스 번호가 중복이면 예외 발생")
    @Test
    void 당첨_번호와_보너스_번호가_중복이면_예외가_발생한다() {
        assertThatThrownBy(() -> Result.from(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)).toDto()), List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_BONUS_DUPLICATION.getMessage());
    }

    @DisplayName("성공: Result 생성")
    @Test
    void Result_생성() {
        List<LottoDto> lottoDtos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)).toDto(),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)).toDto(),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)).toDto()
        );
        List<Integer> winNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        Result result = Result.from(lottoDtos, winNumbers, bonusNumber);
        Map<Rank, Integer> rankCounts = result.toDto().rankCounts();

        assertThat(rankCounts.get(Rank.FIRST)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.SECOND)).isEqualTo(1);
        assertThat(rankCounts.get(Rank.THIRD)).isEqualTo(0);
        assertThat(rankCounts.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(rankCounts.get(Rank.FIFTH)).isEqualTo(1);
    }
}
