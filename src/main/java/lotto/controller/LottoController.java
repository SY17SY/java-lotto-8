package lotto.controller;

import java.util.Arrays;
import java.util.List;
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
        Parser parser = new Parser();

        boolean next = false;

        LottosDto lottosDto = null;
        while (!next) {
            try {
                promptView.printPromptPayment();
                String inputPayment = inputView.inputLine();
                int payment = parser.parsePayment(inputPayment);
                lottosDto = lottoService.generate(payment, new LottoAutoFactory());
                outputView.printLottos(lottosDto);
                next = true;
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }

        next = false;

        List<Integer> winNumbers = List.of();
        while (!next) {
            try {
                promptView.printPromptWinNumber();
                String inputWinNumber = inputView.inputLine();
                winNumbers = parser.parseWinNumber(inputWinNumber);
                next = true;
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }

        next = false;

        int bonusNumber = 0;
        while (!next) {
            try {
                promptView.printPromptBonusNumber();
                String inputBonusNumber = inputView.inputLine();
                bonusNumber = parser.parseBonusNumber(inputBonusNumber);
                next = true;
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }

        next = false;

        ResultDto resultDto = null;
        while(!next) {
            try {
                resultDto = lottoService.calculate(lottosDto, winNumbers, bonusNumber);
                promptView.printPromptResult();
                outputView.printResult(resultDto);
                double profitRate = lottoService.getProfitRate(lottosDto, resultDto);
                outputView.printProfitRate(profitRate);
                next = true;
            } catch (IllegalArgumentException e) {
                errorView.printError(e);
            }
        }
    }
}

class Parser {
    private static final String DELIMITER = ",";

    int parsePayment(String inputPayment) {
        try {
            if (inputPayment == null || inputPayment.isBlank()) {
                throw new IllegalArgumentException(SyntaxErrorMessage.PAYMENT_BLANK.getMessage());
            }
            return Integer.parseInt(inputPayment);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(SyntaxErrorMessage.PAYMENT_SYNTAX.getMessage());
        }
    }

    List<Integer> parseWinNumber(String inputWinNumber) {
        try {
            List<String> inputWins = splitWinNumber(inputWinNumber);
            return inputWins.stream()
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(SyntaxErrorMessage.WIN_NUMBER_SYNTAX.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private List<String> splitWinNumber(String inputWinNumber) {
        if (inputWinNumber == null || inputWinNumber.isBlank()) {
            throw new IllegalArgumentException(SyntaxErrorMessage.WIN_NUMBER_BLANK.getMessage());
        }
        if (!inputWinNumber.contains(DELIMITER)) {
            throw new IllegalArgumentException(SyntaxErrorMessage.WIN_NUMBER_DELIMITER.getMessage());
        }
        return Arrays.stream(inputWinNumber.split(DELIMITER))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();
    }

    int parseBonusNumber(String inputBonusNumber) {
        try {
            if (inputBonusNumber == null || inputBonusNumber.isBlank()) {
                throw new IllegalArgumentException(SyntaxErrorMessage.BONUS_NUMBER_BLANK.getMessage());
            }
            return Integer.parseInt(inputBonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(SyntaxErrorMessage.BONUS_NUMBER_SYNTAX.getMessage());
        }
    }
}
