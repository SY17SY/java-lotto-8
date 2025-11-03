package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Set;
import lotto.dto.WinAndBonusDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinAndBonusTest {
    @DisplayName("실패: 당첨 번호 중 1~45 범위를 이탈하는 숫자가 있으면 예외 발생")
    @Test
    void 당첨_번호에_1_45_범위_외의_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> WinAndBonus.of(List.of(1, 2, 3, 4, 5, 46), 6).toDto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_BONUS_RANGE.getMessage());
    }

    @DisplayName("실패: 보너스 번호가 1~45 범위를 이탈하면 예외 발생")
    @Test
    void 보너스_번호가_1_45_범위_외이면_예외가_발생한다() {
        assertThatThrownBy(() -> WinAndBonus.of(List.of(1, 2, 3, 4, 5, 6), 46).toDto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_BONUS_RANGE.getMessage());
    }

    @DisplayName("실패: 당첨 번호가 6개보다 적으면 예외 발생")
    @Test
    void 당첨_번호가_6개보다_적으면_예외가_발생한다() {
        assertThatThrownBy(() -> WinAndBonus.of(List.of(1, 2, 3, 4, 5), 45).toDto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_NUMBER_LENGTH.getMessage());
    }

    @DisplayName("실패: 당첨 번호가 6개보다 많으면 예외 발생")
    @Test
    void 당첨_번호가_6개보다_많으면_예외가_발생한다() {
        assertThatThrownBy(() -> WinAndBonus.of(List.of(1, 2, 3, 4, 5, 6, 7), 45).toDto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_NUMBER_LENGTH.getMessage());
    }

    @DisplayName("실패: 당첨 번호 중 중복되는 숫자가 있으면 예외 발생")
    @Test
    void 당첨_번호_중_중복_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> WinAndBonus.of(List.of(1, 2, 3, 4, 5, 5), 45).toDto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_NUMBER_DUPLICATION.getMessage());
    }

    @DisplayName("실패: 당첨 번호와 보너스 번호가 중복이면 예외 발생")
    @Test
    void 당첨_번호와_보너스_번호가_중복이면_예외가_발생한다() {
        assertThatThrownBy(() -> WinAndBonus.of(List.of(1, 2, 3, 4, 5, 6), 6).toDto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WIN_BONUS_DUPLICATION.getMessage());
    }

    @DisplayName("성공: WinAndBonus 생성")
    @Test
    void Win_and_bonus_생성() {
        WinAndBonusDto winAndBonusDto = WinAndBonus.of(List.of(1, 2, 3, 4, 5, 6), 7).toDto();
        assertThat(winAndBonusDto.winNumbers()).hasSize(6);
        assertThat(winAndBonusDto.bonusNumber()).isEqualTo(7);
    }

    @DisplayName("성공: WinAndBonus 생성 (오름차순)")
    @Test
    void Win_and_bonus_생성_오름차순() {
        WinAndBonusDto winAndBonusDto = WinAndBonus.of(List.of(1, 2, 3, 4, 6, 5), 7).toDto();
        assertThat(winAndBonusDto.winNumbers()).isEqualTo(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(winAndBonusDto.bonusNumber()).isEqualTo(7);
    }
}
