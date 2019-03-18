package calculator.team07.presenter;

import java.util.ArrayList;
import java.util.List;

import calculator.team07.model.Entity.Result;
import calculator.team07.model.HistoryInteractor;
import calculator.team07.model.IHistoryListener;

public class HistoryPresenter implements IHistoryListener {

    private HistoryInteractor mHistoryInteractor;
    private IHistoryView mIHistoryView;

    public HistoryPresenter(IHistoryView iHistoryView){
        mIHistoryView = iHistoryView;
        mHistoryInteractor = new HistoryInteractor(this);
    }

    public void deleteResult(int index){
        mHistoryInteractor.deleteResult(index);
    }

    public void getPreviousResult(int index){
        mHistoryInteractor.getPreviousResult(index);
    }

    public  void getListResut(){
        mHistoryInteractor.getListResult();
    }


    @Override
    public void onDeleteResultFailure() {
        mIHistoryView.displayDeleteResultFailure();
    }

    @Override
    public void onDeleteResultSuccess() {
        mIHistoryView.displayDeleteResultSuccess();
    }

    @Override
    public void onLoadListResultSuccess(List<Result> lstAnswer) {
        mIHistoryView.displayListResultSuccess(lstAnswer);
    }

    @Override
    public void onLoadListResultFailure() {
        mIHistoryView.displayListResultFailure();
    }

    @Override
    public void onLoadPreviousResultSuccess(String textMath, String screenTextMath, String textAns, int index) {
        mIHistoryView.displayPreviousResultSuccess(textMath, screenTextMath, textAns, index);
    }

    @Override
    public void onLoadPreviousResultFailure() {
        mIHistoryView.displayPreviousResultFailure();
    }
}
