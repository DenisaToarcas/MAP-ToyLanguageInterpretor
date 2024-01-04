package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.types.RefType;
import model.types.Type;
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

    @Override
    public Exp deepCopy()
    {
        return new rHExp(this.exp.deepCopy());
    }

    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ=exp.typeCheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }
}
