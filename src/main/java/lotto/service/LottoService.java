package lotto.service;

import java.util.List;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.Result;
import lotto.dto.LottosDto;
import lotto.dto.ResultDto;
import lotto.service.mapper.LottosMapper;
import lotto.service.mapper.ResultMapper;

public class LottoService {
    private static final int PRICE = 1000;

    public LottosDto generate(int payment, LottoFactory factory) {
        Lottos lottos = Lottos.from(payment, factory);
        return LottosMapper.toDto(lottos);
    }

    public ResultDto calculate(LottosDto lottosDto, List<Integer> winNumbers, int bonusNumber) {
        Result result = Result.from(lottosDto, winNumbers, bonusNumber);
        return ResultMapper.toDto(result);
    }

    public double getProfitRate(LottosDto lottosDto, ResultDto resultDto) {
        int totalPrice = lottosDto.count() * PRICE;
        return resultDto.profit() * 100.0 / totalPrice;
    }
}
