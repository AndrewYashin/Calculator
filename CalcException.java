package calculatorm;

public class CalcException extends Exception {
    public CalcException() {
        System.out.println("The expression is incorrectly set");
    }

    public CalcException(String message){
        this();
        System.out.println(message);
    }
}
