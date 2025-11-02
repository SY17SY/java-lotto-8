package lotto.service.mapper;

import lotto.domain.WinAndBonus;
import lotto.dto.WinAndBonusDto;

public class WinAndBonusMapper {
    public static WinAndBonusDto toDto(WinAndBonus winAndBonus) {
        return winAndBonus.toDto();
    }
}
