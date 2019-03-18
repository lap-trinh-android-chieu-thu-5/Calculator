package calculator.team07.model;

public interface IMainListener {
    void onLoadCalculatorFailure(String msgError);

    void onLoadCalculatorSuccess(String textAns);

    void onLoadPreviousResultFailure(String msgError);

    void onLoadPreviousResultSuccess(String textMath, String screenTextMath, String textAns, int index);
}
