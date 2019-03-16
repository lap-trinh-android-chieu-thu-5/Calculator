package calculator.team07.model;

public interface IMainListener {
    public void onLoadCalculatorFailure(String msgError);
    public void onLoadCalculatorSuccess(String textAns);

    public void onLoadPreviousResultFailure(String msgError);
    public void onLoadPreviousResultSuccess(String textMath, String screenTextMath,String textAns, int index);
}
