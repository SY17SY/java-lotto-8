package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;

public class LottoAutoFactory implements LottoFactory {
    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        List<Integer> sorted = numbers.stream()
                .sorted()
                .toList();
        return new Lotto(sorted);
    }
}
