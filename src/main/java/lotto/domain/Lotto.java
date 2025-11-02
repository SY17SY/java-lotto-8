package lotto.domain;

import java.util.List;
import lotto.dto.LottoDto;
import lotto.view.ErrorMessage;

public class Lotto {
    private static final int NUMBER_LENGTH = 6;
    private static final int NUMBER_START = 1;
    private static final int NUMBER_END = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    public LottoDto toDto() {
        return new LottoDto(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_BLANK.getMessage());
        }
        if (numbers.size() != NUMBER_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_LENGTH.getMessage());
        }
        if (numbers.stream().anyMatch(n -> n < NUMBER_START || n > NUMBER_END)) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_RANGE.getMessage());
        }
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_DUPLICATION.getMessage());
        }
    }
}
