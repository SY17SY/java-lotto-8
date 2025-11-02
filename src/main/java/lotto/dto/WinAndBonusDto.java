package lotto.dto;

import java.util.Set;

public record WinAndBonusDto(Set<Integer> winNumbers, int bonusNumber) {
}
