package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.view.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {
    @DisplayName("실패: 지불 금액이 0보다 작으면 예외 발생")
    @Test
    void 지불_금액이_음수면_예외가_발생한다() {
        int payment = -1000;
        assertThatThrownBy(() -> Lottos.from(payment, new LottoAutoFactory()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PAYMENT_NEGATIVE.getMessage());
    }

    @DisplayName("실패: 지불 금액이 1,000원 단위가 아니면 예외 발생")
    @Test
    void 지불_금액이_1000원_단위가_아니면_예외가_발생한다() {
        int payment = 1300;
        assertThatThrownBy(() -> Lottos.from(payment, new LottoAutoFactory()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PAYMENT_REMAINDER.getMessage());
    }
}
