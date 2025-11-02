package lotto.controller;

import lotto.domain.LottoAutoFactory;
import lotto.dto.LottosDto;
import lotto.service.LottoService;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.PromptView;

public class LottoController {
    private final PromptView promptView;
    private final InputView inputView;
    private final ErrorView errorView;
    private final LottoService lottoService;

    public LottoController(PromptView promptView, InputView inputView, ErrorView errorView, LottoService lottoService) {
        this.promptView = promptView;
        this.inputView = inputView;
        this.errorView = errorView;
        this.lottoService = lottoService;
    }

    public void run() {
        Parser parser = new Parser(errorView);

        boolean success = false;

        while (!success) {
            try {
                promptView.printPromptPayment();
                String inputPayment = inputView.inputPayment();
                int payment = parser.parsePayment(inputPayment);
                LottosDto lottosDto = lottoService.generate(payment, new LottoAutoFactory());
                success = true;
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }
    }
}

class Parser {
    private final ErrorView errorView;

    Parser(ErrorView errorView) {
        this.errorView = errorView;
    }

    int parsePayment(String inputPayment) {
        return Integer.parseInt(inputPayment);
    }
}
