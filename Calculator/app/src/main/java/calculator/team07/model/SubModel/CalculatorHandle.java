package calculator.team07.model.SubModel;

import java.util.ArrayList;
import java.util.List;

import calculator.team07.model.Entity.Result;

public final class CalculatorHandle {

    //Danh sach lich su ket qua
    public static List<Result> sLstAnswer = new ArrayList<>();

    //Goi doi tuong chuyen tien to sang hau to de su dung
    private static InfixToPostfix sInfixToPostfix = new InfixToPostfix();

    //Ket tra co phai vua tinh toan xong k
    public static boolean sCheckSubmit = false;


    //Thuc hien tinh toan, tra ve entity result
    public static Result caculate(String textMath, String screenTextMath, String textAns) {
        String elementMath[] = {""};
        if (textMath.length() > 0) {
            if (!sInfixToPostfix.check_error) {
                elementMath = sInfixToPostfix.processString(textMath);  //	separacte expression
            }
            if (!sInfixToPostfix.check_error) {
                elementMath = sInfixToPostfix.postfix(elementMath); // 	convert expression to postfix
            }
            if (!sInfixToPostfix.check_error) {
                textAns = sInfixToPostfix.valueMath(elementMath); //get result
            }
            sCheckSubmit = true;
        }

        if (sInfixToPostfix.check_error) {
            sInfixToPostfix.check_error = false;
            return null;
        }
        return new Result(textMath, screenTextMath, textAns);
    }

    //them ket qua vao danh sach lich su
    public static void  addResultToArrayList(Result result) {
        sLstAnswer.add(result);
    }

    public static Result  getPreviousResult(int index) {
        Result result = sLstAnswer.get(index);
        return result;
    }

    //Xoa ket qua khoi lich su
    public  static  void deleteResult(int index){
        sLstAnswer.remove(index);
    }

    //Lay danh sach lich su
    public  static  List<Result> getListResult(){
        return sLstAnswer;
    }
}
