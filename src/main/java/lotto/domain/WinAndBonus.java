package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.dto.WinAndBonusDto;

public class WinAndBonus {
    private static final int NUMBER_LENGTH = 6;
    private static final int NUMBER_START = 1;
    private static final int NUMBER_END = 45;

    private final Set<Integer> winNumbers;
    private final int bonusNumber;

    public WinAndBonus(Set<Integer> winNumbers, int bonusNumber) {
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinAndBonus of(List<Integer> winNumbers, int bonusNumber) {
        validateWinNumbers(winNumbers);
        validateWinAndBonusNumber(winNumbers, bonusNumber);
        List<Integer> sortedWinNumbers = winNumbers.stream().sorted().toList();
        return new WinAndBonus(new HashSet<>(sortedWinNumbers), bonusNumber);
    }

    private static void validateWinNumbers(List<Integer> winNumbers) {
        if (winNumbers.size() != NUMBER_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.WIN_NUMBER_LENGTH.getMessage());
        }
        if (winNumbers.stream().distinct().count() != winNumbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.WIN_NUMBER_DUPLICATION.getMessage());
        }
    }

    private static void validateWinAndBonusNumber(List<Integer> winNumbers, int bonusNumber) {
        winNumbers.forEach(WinAndBonus::validateNumber);
        validateNumber(bonusNumber);

        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.WIN_BONUS_DUPLICATION.getMessage());
        }
    }

    private static void validateNumber(int number) {
        if (number < NUMBER_START || number > NUMBER_END) {
            throw new IllegalArgumentException(ErrorMessage.WIN_BONUS_RANGE.getMessage());
        }
    }

    public WinAndBonusDto toDto() {
        return new WinAndBonusDto(winNumbers, bonusNumber);
    }
}
