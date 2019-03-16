package calculator.team07.presenter;

import calculator.team07.model.IMainListener;
import calculator.team07.model.MainInteractor;

public class MainPresenter implements IMainListener {

    private MainInteractor mMainInteractor;
    private IMainView mIMainView;
    public  MainPresenter(IMainView iMainView){
        mIMainView = iMainView;
        mMainInteractor = new MainInteractor(this);
    }

    @Override
    public void onLoadCalculatorFailure(String msgError) {
        mIMainView.displayCalculatorFailure(msgError);
    }

    @Override
    public void onLoadCalculatorSuccess(String textAns) {
        mIMainView.displayCalculatorSuccess(textAns);
    }

    @Override
    public void onLoadPreviousResultFailure(String msgError) {
        mIMainView.displayPreviousResultFailure(msgError);
    }

    @Override
    public void onLoadPreviousResultSuccess(String textMath, String screenTextMath, String textAns, int index) {
        mIMainView.displayPreviousResultSuccess(textMath, screenTextMath, textAns, index);
    }

    public void calculate(String textMath, String screenTextMath, String textAns){
        mMainInteractor.calculate(textMath, screenTextMath, textAns);
    }
    public  void getPreviousResult(int i){
        mMainInteractor.getPreviousResult(i);
    }
}
