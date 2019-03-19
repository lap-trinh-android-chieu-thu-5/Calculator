package calculator.team07.presenter;

public interface IMainView {
    void displayCalculatorFailure(String msgError);

    void displayCalculatorSuccess(String textAns);

    void displayPreviousResultFailure(String msgError);

    void displayPreviousResultSuccess(String textMath, String screenTextMath, String textAns, int index);
}
