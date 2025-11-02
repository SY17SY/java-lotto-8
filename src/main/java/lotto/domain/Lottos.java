package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.dto.LottoDto;
import lotto.dto.LottosDto;
import lotto.view.ErrorMessage;

public class Lottos {
    private final int count;
    private final List<Lotto> lottos;

    Lottos(int count, List<Lotto> lottos) {
        this.count = count;
        this.lottos = lottos;
    }

    public static Lottos from(int payment, LottoFactory factory) {
        validate(payment);
        int count = payment / 1000;
        List<Lotto> newLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newLottos.add(factory.generate());
        }
        return new Lottos(count, newLottos);
    }

    public LottosDto toDto() {
        List<LottoDto> lottoDtos = lottos.stream()
                .map(Lotto::toDto)
                .toList();
        return new LottosDto(count, lottoDtos);
    }

    private static void validate(int payment) {
        if (payment < 0) {
            throw new IllegalArgumentException(ErrorMessage.PAYMENT_NEGATIVE.getMessage());
        }
        if (payment % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.PAYMENT_REMAINDER.getMessage());
        }
    }
}
