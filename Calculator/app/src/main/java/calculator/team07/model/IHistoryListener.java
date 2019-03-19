package calculator.team07.model;

import java.util.ArrayList;
import java.util.List;

import calculator.team07.model.Entity.Result;

public interface IHistoryListener {
    void onDeleteResultFailure();

    void onDeleteResultSuccess();

    void onLoadListResultSuccess(List<Result> lstAnswer);

    void onLoadListResultFailure();

    void onLoadPreviousResultSuccess(String textMath, String screenTextMath, String textAns, int index);

    void onLoadPreviousResultFailure();

}
