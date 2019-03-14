package calculator.team07.model.SubModel;

public final class CalculatorHandle {

    private static InfixToPostfix sInfixToPostfix = new InfixToPostfix();
    public static String textAns = "";
    public static String textMath = "";
    public static String screenTextMath = "";
    public static int checkSubmit = 0;

    public static String Caculate(String elementMath[]) {
        if(textMath.length() > 0)
        {
                if (!sInfixToPostfix.check_error) {
                    elementMath = sInfixToPostfix.processString(textMath);        //	separacte expression
                }
                if (!sInfixToPostfix.check_error) {
                    elementMath = sInfixToPostfix.postfix(elementMath);        // 	convert expression to postfix
                }
                if (!sInfixToPostfix.check_error) {
                    textAns = sInfixToPostfix.valueMath(elementMath);        //get result
                }
                checkSubmit = 1;
                textMath = textAns;
                screenTextMath = textAns;
        }

        if(sInfixToPostfix.check_error){
            return "Math Error !";
        }
        return textAns;
    }

}
