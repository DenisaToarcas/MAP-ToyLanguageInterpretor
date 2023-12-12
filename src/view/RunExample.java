package view;
import controller.*;
import exception.MyException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() throws Exception {
        try{
            ctr.allStep();
        }
     catch (MyException exception) {
        System.out.println(exception.toString());
    } catch (Exception e) {
            System.out.println(e);
    } //here you must treat the exceptions that can not be solved in the controller
    }
}