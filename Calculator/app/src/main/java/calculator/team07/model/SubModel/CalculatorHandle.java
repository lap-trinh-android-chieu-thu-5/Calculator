package calculator.team07.model.SubModel;

public final class CalculatorHandle {

    private  static  InfixToPostfix sInfixToPostfix = new InfixToPostfix();
    private  static  String textAns = "";
    private  static  String textMath = "";
    private  static  String screenTextMath = "";

    public static String Caculate(String elementMath[], String expression){
        if (!sInfixToPostfix.check_error) elementMath = sInfixToPostfix.processString(expression);		//	tach bieu thuc thanh cac phan tu
        if (!sInfixToPostfix.check_error) elementMath = sInfixToPostfix.postfix(elementMath);		// 	dua cac phan tu ve dang postfix
        if (!sInfixToPostfix.check_error) textAns = sInfixToPostfix.valueMath(elementMath);		//lay gia tri
        textMath = textAns;
        screenTextMath = textAns;

        return  textAns;
    }

}
