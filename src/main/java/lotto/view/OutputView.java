package lotto.view;

import lotto.domain.Rank;
import lotto.dto.LottoDto;
import lotto.dto.LottosDto;
import lotto.dto.ResultDto;

public class OutputView {
    private static final String LOTTOS_SUFFIX = "개를 구매했습니다.";
    private static final String PROFIT_RATE_PREFIX = "총 수익률은 ";
    private static final String PROFIT_RATE_SUFFIX = "%입니다.";

    public void printLottos(LottosDto lottosDto) {
        System.out.println();
        System.out.println(lottosDto.count() + LOTTOS_SUFFIX);
        for (LottoDto lottoDto : lottosDto.lottos()) {
            System.out.println(lottoDto.numbers());
        }
        System.out.println();
    }

    public void printResult(ResultDto resultDto) {
        for (Rank rank : resultDto.rankCounts().keySet()) {
            if (rank == Rank.MISS) {
                continue;
            }
            int count = resultDto.rankCounts().get(rank);
            System.out.println(rank.getPrompt() + String.format("%d개", count));
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.println(PROFIT_RATE_PREFIX + String.format("%.1f", profitRate) + PROFIT_RATE_SUFFIX);
    }
}
