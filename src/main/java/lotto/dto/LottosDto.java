package lotto.dto;

import java.util.List;

public record LottosDto(int count, List<LottoDto> lottos) {
}
