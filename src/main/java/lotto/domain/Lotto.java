package lotto.domain;

import java.util.List;
import lotto.dto.LottoDto;
import lotto.view.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public LottoDto toDto() {
        return new LottoDto(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_LENGTH.getMessage());
        }
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_DUPLICATION.getMessage());
        }
    }
}
