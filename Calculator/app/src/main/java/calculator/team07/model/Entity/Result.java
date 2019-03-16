package calculator.team07.model.Entity;

public class Result {
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
