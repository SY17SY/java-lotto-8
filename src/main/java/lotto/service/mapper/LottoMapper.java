package lotto.service.mapper;

import lotto.domain.Lotto;
import lotto.dto.LottoDto;

public class LottoMapper {
    public static LottoDto toDto(Lotto lotto) {
        return lotto.toDto();
    }
}
