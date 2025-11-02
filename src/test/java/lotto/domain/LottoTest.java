package lotto.domain;

import lotto.view.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("실패: 로또 번호의 개수가 6개가 넘어가면 예외 발생")
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.LOTTO_NUMBERS_LENGTH.getMessage());
    }

    @DisplayName("실패: 로또 번호에 중복된 숫자가 있으면 예외 발생")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.LOTTO_NUMBERS_DUPLICATION.getMessage());
    }

    @DisplayName("성공: 로또 생성")
    @Test
    void 로또_생성() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.toDto().numbers())
                .hasSize(6)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("성공: 로또 번호 오름차순")
    @Test
    void 로또_번호_오름차순() {
        List<Integer> numbers = List.of(1, 2, 3, 6, 5, 4);
        Lotto lotto = new Lotto(numbers);
        assertThat(lotto.toDto().numbers())
                .hasSize(6)
                .containsExactly(1, 2, 3, 4, 5, 6);
    }
}
