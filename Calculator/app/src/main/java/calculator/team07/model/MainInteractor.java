package calculator.team07.model;

import calculator.team07.model.Entity.Result;
import calculator.team07.model.SubModel.CalculatorHandle;

public class MainInteractor {
    private IMainListener mIMainListener;

    public  MainInteractor(IMainListener iMainListener){
        mIMainListener = iMainListener;
    }

    public void calculate(String textMath, String screenTextMath, String textAns){
        Result result = CalculatorHandle.caculate(textMath, screenTextMath , textAns);
        if(result == null){
            mIMainListener.onLoadCalculatorFailure("Math Error !");
        }else{
            mIMainListener.onLoadCalculatorSuccess(result.Answer);
            CalculatorHandle.addResultToArrayList(result);
        }
    }
    public  void getPreviousResult(int i){
        try{
            Result result = CalculatorHandle.getPreviousResult(i);
            if(result != null) {
                mIMainListener.onLoadPreviousResultSuccess(result.TextMath,result.ScreenMath, result.Answer,i);
            }
        }catch (IndexOutOfBoundsException ex){
            return;
        }

    }
}
