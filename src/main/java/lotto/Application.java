package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.ErrorView;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.PromptView;

public class Application {
    public static void main(String[] args) {
        PromptView promptView = new PromptView();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ErrorView errorView = new ErrorView();
        LottoService lottoService = new LottoService();

        LottoController controller = new LottoController(
                promptView,
                inputView,
                outputView,
                errorView,
                lottoService
        );

        controller.run();
    }
}
