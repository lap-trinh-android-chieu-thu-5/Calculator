package calculator.team07.model.Entity;

import java.io.Serializable;

public class Result implements Serializable {
    public String TextMath="";
    public String ScreenMath="";
    public String Answer="";
    public  Result(String textMath, String screenMath, String answer)
    {
        this.TextMath = textMath;
        this.ScreenMath = screenMath;
        this.Answer = answer;
    }
}
