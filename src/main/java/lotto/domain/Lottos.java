package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.dto.LottoDto;
import lotto.dto.LottosDto;

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
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(factory.generate());
        }
        return new Lottos(count, lottos);
    }

    public LottosDto toDto() {
        List<LottoDto> lottoDtos = lottos.stream()
                .map(Lotto::toDto)
                .toList();
        return new LottosDto(count, lottoDtos);
    }

    private static void validate(int payment) {
        if (payment < 0) {

        }
        if (payment % 1000 != 0) {

        }
    }
}
