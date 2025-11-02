package lotto.service.mapper;

import lotto.domain.Lottos;
import lotto.dto.LottosDto;

public class LottosMapper {
    public static LottosDto toDto(Lottos lottos) {
        return lottos.toDto();
    }
}
