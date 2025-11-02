package lotto.view;

import lotto.domain.Rank;
import lotto.dto.LottoDto;
import lotto.dto.LottosDto;
import lotto.dto.ResultDto;

public class OutputView {
    public void printLottos(LottosDto lottosDto) {
        System.out.println(lottosDto.count() + OutputMessage.LOTTOS.getMessage());
        for (LottoDto lottoDto : lottosDto.lottos()) {
            System.out.println(lottoDto.numbers());
        }
        System.out.println();
    }

    public void printResult(ResultDto resultDto) {
        for (Rank rank : resultDto.rankCounts().keySet()) {
            int count = resultDto.rankCounts().get(rank);
            System.out.println(rank.getPrompt() + String.format("%d개", count));
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.println(OutputMessage.PROFIT_RATE_PREFIX.getMessage() + String.format("%.1f", profitRate) + OutputMessage.PROFIT_RATE_SUFFIX);
    }
}
