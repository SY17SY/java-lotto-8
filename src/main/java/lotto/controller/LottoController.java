package lotto.controller;

import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.PromptView;

public class LottoController {
    private final PromptView promptView;
    private final InputView inputView;
    private final ErrorView errorView;

    public LottoController(PromptView promptView, InputView inputView, ErrorView errorView) {
        this.promptView = promptView;
        this.inputView = inputView;
        this.errorView = errorView;
    }

    public void run() {
        Parser parser = new Parser(errorView);

        promptView.printPromptPayment();
        String inputPayment = inputView.inputPayment();
        int payment = parser.parsePayment(inputPayment);
    }
}

class Parser {
    private final ErrorView errorView;

    Parser(ErrorView errorView) {
        this.errorView = errorView;
    }

    int parsePayment(String inputPayment) {
        try {
            return Integer.parseInt(inputPayment);
        } catch (NumberFormatException e) {
            errorView.printPaymentSyntaxError();
        }
    }
}
