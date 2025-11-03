package lotto.view;

public class ErrorView {
    public void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }
}
