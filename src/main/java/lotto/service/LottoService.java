package lotto.service;

import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.dto.LottosDto;
import lotto.service.mapper.LottosMapper;

public class LottoService {
    public LottosDto generate(int payment, LottoFactory factory) {
        Lottos lottos = Lottos.from(payment, factory);
        return LottosMapper.toDto(lottos);
    }
}
