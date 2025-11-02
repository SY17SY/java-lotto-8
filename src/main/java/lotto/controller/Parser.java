package lotto.controller;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final String DELIMITER = ",";

    public static int parsePayment(String inputPayment) {
        try {
            if (inputPayment == null || inputPayment.isBlank()) {
                throw new IllegalArgumentException(SyntaxErrorMessage.PAYMENT_BLANK.getMessage());
            }
            return Integer.parseInt(inputPayment);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(SyntaxErrorMessage.PAYMENT_SYNTAX.getMessage());
        }
    }

    public static List<Integer> parseWinNumber(String inputWinNumber) {
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

    private static List<String> splitWinNumber(String inputWinNumber) {
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

    public static int parseBonusNumber(String inputBonusNumber) {
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
