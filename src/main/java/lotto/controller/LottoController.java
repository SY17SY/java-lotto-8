package lotto.controller;

import java.util.List;
import java.util.function.Function;
import lotto.domain.LottoAutoFactory;
import lotto.dto.LottosDto;
import lotto.dto.ResultDto;
import lotto.service.LottoService;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.PromptView;

public class LottoController {
    private final PromptView promptView;
    private final InputView inputView;
    private final OutputView outputView;
    private final ErrorView errorView;
    private final LottoService lottoService;

    public LottoController(PromptView promptView, InputView inputView, OutputView outputView, ErrorView errorView, LottoService lottoService) {
        this.promptView = promptView;
        this.inputView = inputView;
        this.outputView = outputView;
        this.errorView = errorView;
        this.lottoService = lottoService;
    }

    public void run() {
        LottosDto lottosDto;
        while (true) {
            try {
                int payment = askUntilValid(promptView::printPromptPayment, Parser::parsePayment);
                lottosDto = lottoService.generate(payment, new LottoAutoFactory());
                break;
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }
        outputView.printLottos(lottosDto);

        ResultDto resultDto;
        while (true) {
            try {
                List<Integer> winNumbers = askUntilValid(promptView::printPromptWinNumber, Parser::parseWinNumber);
                int bonusNumber = askUntilValid(promptView::printPromptBonusNumber, Parser::parseBonusNumber);
                resultDto = lottoService.calculate(lottosDto, winNumbers, bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }
        promptView.printPromptResult();
        outputView.printResult(resultDto);

        double profitRate = lottoService.getProfitRate(lottosDto, resultDto);
        outputView.printProfitRate(profitRate);
    }

    private <T> T askUntilValid(Runnable prompt, Function<String, T> parse) {
        while (true) {
            try {
                prompt.run();
                String input = inputView.inputLine();
                return parse.apply(input);
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }
    }
}
