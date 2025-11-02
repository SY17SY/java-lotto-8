package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
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
        LottosDto lottosDto = null;
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

        List<Integer> winNumbers = List.of();
        int bonusNumber = 0;
        ResultDto resultDto = null;

        while (true) {
            try {
                winNumbers = askUntilValid(promptView::printPromptWinNumber, Parser::parseWinNumber);
                bonusNumber = askUntilValid(promptView::printPromptBonusNumber, Parser::parseBonusNumber);
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

class Parser {
    private static final String DELIMITER = ",";

    static int parsePayment(String inputPayment) {
        try {
            if (inputPayment == null || inputPayment.isBlank()) {
                throw new IllegalArgumentException(SyntaxErrorMessage.PAYMENT_BLANK.getMessage());
            }
            return Integer.parseInt(inputPayment);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(SyntaxErrorMessage.PAYMENT_SYNTAX.getMessage());
        }
    }

    static List<Integer> parseWinNumber(String inputWinNumber) {
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

    static private List<String> splitWinNumber(String inputWinNumber) {
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

    static int parseBonusNumber(String inputBonusNumber) {
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
