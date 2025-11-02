package lotto.view;

import lotto.dto.LottoDto;
import lotto.dto.LottosDto;

public class OutputView {
    public void printLottos(LottosDto lottosDto) {
        System.out.println(lottosDto.count() + "개를 구매했습니다.");
        for (LottoDto lottoDto : lottosDto.lottos()) {
            System.out.println(lottoDto.numbers());
        }
        System.out.println();
    }
}
