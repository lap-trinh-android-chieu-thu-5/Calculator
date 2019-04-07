package calculator.team07.model;

import java.util.ArrayList;
import java.util.List;

import calculator.team07.model.Entity.Result;
import calculator.team07.model.SubModel.CalculatorHandle;

public class HistoryInteractor {
    private IHistoryListener mIHistoryListener;

    public  HistoryInteractor(IHistoryListener iHistoryListener){
        mIHistoryListener = iHistoryListener;
    }

    //Xoa ket qua trong danh sach lich su
    public  void deleteResult(int index){
        try{
            CalculatorHandle.deleteResult(index);
            mIHistoryListener.onDeleteResultSuccess();
        }catch (IndexOutOfBoundsException ex){
            mIHistoryListener.onDeleteResultFailure();
        }
    }

    //lay ket qua tai vi tri yeu cau trong danh sach lich su
    public  void getPreviousResult(int i){
        try{
            Result result = CalculatorHandle.getPreviousResult(i);
            if(result != null) {
                mIHistoryListener.onLoadPreviousResultSuccess(result.TextMath,result.ScreenMath, result.Answer,i);
            }
        }catch (IndexOutOfBoundsException ex){
            return;
        }
    }

    //Lay toan bo danh sach lich su
    public  void getListResult(){
        List<Result> lstAnswer = CalculatorHandle.getListResult();
        mIHistoryListener.onLoadListResultSuccess(lstAnswer);
    }
}
