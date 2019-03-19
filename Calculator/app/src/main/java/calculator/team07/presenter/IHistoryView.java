package calculator.team07.presenter;

import java.util.ArrayList;
import java.util.List;

import calculator.team07.model.Entity.Result;

public interface IHistoryView {
    void displayDeleteResultFailure();

    void displayDeleteResultSuccess();

    void displayListResultSuccess(List<Result> lstAnswer);

    void displayListResultFailure();

    void displayPreviousResultSuccess(String textMath, String screenTextMath, String textAns, int index);

    void displayPreviousResultFailure();
}
