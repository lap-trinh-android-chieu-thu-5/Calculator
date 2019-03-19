package calculator.team07.model.SubModel;

import java.util.ArrayList;

import calculator.team07.model.Entity.Result;

public final class CalculatorHandle {

    public static ArrayList<Result> sLstAnswer = new ArrayList<>();
    private static InfixToPostfix sInfixToPostfix = new InfixToPostfix();
    public static boolean sCheckSubmit = false;

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
            return null;
        }
        return new Result(textMath, screenTextMath, textAns);
    }
    public static void  addResultToArrayList(Result result)
    {
        sLstAnswer.add(result);
    }
    public static Result  getPreviousResult(int index)
    {
        Result result = sLstAnswer.get(index);
        return result;
    }
}
