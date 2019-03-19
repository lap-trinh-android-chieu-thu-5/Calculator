package calculator.team07.presenter;

public interface IMainView {
    public void displayCalculatorFailure(String msgError);
    public void displayCalculatorSuccess(String textAns);

    public void displayPreviousResultFailure(String msgError);
    public void displayPreviousResultSuccess(String textMath, String screenTextMath,String textAns, int index);
}
