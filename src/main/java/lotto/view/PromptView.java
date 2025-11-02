package lotto.view;

public class PromptView {
    public void printPromptPayment() {
        System.out.println(PromptMessage.PAYMENT.getMessage());
    }

    public void printPromptWinNumber() {
        System.out.println(PromptMessage.WIN_NUMBER.getMessage());
    }

    public void printPromptBonusNumber() {
        System.out.println(PromptMessage.BONUS_NUMBER.getMessage());
    }

    public void printPromptResult() {
        System.out.println(PromptMessage.RESULT.getMessage());
    }
}
