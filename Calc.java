package calculatorm;

import java.util.Arrays;
import java.util.List;

public class Calc {
    private int num1, num2; //numbers in the expression
    private String operator;    //operator in the expression, allowed: +-*/

    //--execution of an arithmetic expression (only integers in the answer)
    private int calcExp(int n1, String op, int n2){
        int res;
        switch (op) {
            case "+":
                res = n1+n2;
                break;
            case "-":
                res = n1-n2;
                break;
            case "*":
                res = n1*n2;
                break;
            case "/":
                res = n1/n2;
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }

    //---public method with checks and with the output of the result
    public String result(String exp) throws CalcException {
        boolean isRomanExp;     // ---A sign that the numbers are Roman
        Parse parse = new Parse();

        //---splitting the original String expression by separator " "
        List<String> expItems = Arrays.asList(exp.split(" "));

        //---checking that 3 elements were created: number 1, operator, number2, otherwise an exception
        if (expItems.size() != 3) {
            throw new CalcException("ERROR. The expression should have the form: \"Number1 Operator Number2\",separated by a space...");
        }

        //--- checking the operator, should be: + - * /
        if (parse.checkOperator(expItems.get(1))) {
            operator = expItems.get(1);
        } else {
            throw new CalcException("ERROR. Operator '" + expItems.get(1) + "' nor right, should be: + - * / ");
        }

        //---checking the numbers, must be both Arabic or both Roman
        if (parse.isNumeric(expItems.get(0)) && parse.isNumeric(expItems.get(2))) {      //---check that both numbers are Arabic
            num1 = Integer.parseInt(expItems.get(0));
            num2 = Integer.parseInt(expItems.get(2));
            isRomanExp = false;
        } else if (parse.isRoman(expItems.get(0)) && parse.isRoman(expItems.get(2))) {   //---check that both numbers are Roman
            num1 = parse.romeToArabConvert(expItems.get(0));
            num2 = parse.romeToArabConvert(expItems.get(2));
            isRomanExp = true;
        } else {    //--- the numbers don't match
            throw new CalcException("ERROR. The numbers should be both Arabic or both Roman");
        }

        //---checking the numbers should be from 1 to 10 inclusive
        if (!(num1 >= 1 && num1 <= 10)) {
            throw new CalcException("ERROR.  The number #1 should be be from 1 to 10 or from I to X inclusive");
        }

        if (!(num2 >= 1 && num2 <= 10)) {
            throw new CalcException("ERROR.  The number #2 should be from 1 to 10 or from I to X inclusive");
        }

        //--- get the result
        int res = calcExp(num1, operator, num2);

        //---  convert the numbers and return the result
        if (isRomanExp) {
            String sign = res < 0 ? "-" : "";
            return sign + parse.arabToRomeConvert(Math.abs(res));
        }

        //--- return the answer - an Arabic number
        return String.valueOf(res);
    }
}
