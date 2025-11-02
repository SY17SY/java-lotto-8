package lotto.controller;

import java.util.List;
import lotto.domain.LottoAutoFactory;
import lotto.dto.LottosDto;
import lotto.service.LottoService;
import lotto.view.ErrorMessage;
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
        Parser parser = new Parser();

        boolean success = false;

        while (!success) {
            try {
                promptView.printPromptPayment();
                String inputPayment = inputView.inputLine();
                int payment = parser.parsePayment(inputPayment);
                LottosDto lottosDto = lottoService.generate(payment, new LottoAutoFactory());
                success = true;
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }

        success = false;

        while (!success) {
            try {
                promptView.printPromptWinNumber();
                String inputWinNumber = inputView.inputLine();
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }
    }
}

class Parser {
    int parsePayment(String inputPayment) {
        try {
            return Integer.parseInt(inputPayment);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.PAYMENT_SYNTAX.getMessage());
        }
    }

    List<String> parseWinNumber(String inputWinNumber) {

    }
}
