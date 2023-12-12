package exception;

public class MyException extends Exception{
    private String msg;
    public MyException(String msg){
        this.msg = msg;
    }
    public String toString(){
        return "Exception caught: " + this.msg;
    }
}
