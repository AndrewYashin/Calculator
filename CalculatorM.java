package calculatorm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatorM {

    public static void main(String[] args) {
        try{
            System.out.println("Calculator for expressions of the form:: \"Number1 Operation Number2\", separated by a space. Numbers from 1 to 10 or from I to X inclusive are allowed.. Operations: + - * /");
            System.out.print("Enter the expression: ");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            String calcString = bReader.readLine();

            Calc calc = new Calc();
            String result = calc.result(calcString);
            System.out.println("Result: " + result);
        }
        catch(CalcException | IOException e){

        }

    }

}


