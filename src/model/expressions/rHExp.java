package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.values.RefValue;
import model.values.Value;

public class rHExp implements Exp{
    private final Exp exp;

    public rHExp(Exp exp)
    {
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "rH(" + this.exp.toString() + ")";
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws MyException
    {
        Value value = this.exp.eval(tbl, heap);
        if (value instanceof RefValue)
        {
            int address = ((RefValue) value).getAddress();
            if (heap.isDefined(address))
            {
                return heap.lookup(address);
            }
            else
                throw new MyException("The address was not defined in the Heap!!");
        }
        else
            throw new MyException("The value was not evaluated to RefValue!!!");
    }

    public Exp deepCopy()
    {
        return new rHExp(this.exp.deepCopy());
    }
}
