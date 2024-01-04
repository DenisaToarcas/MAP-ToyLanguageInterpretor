package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.types.*;
import model.values.*;

public class ValueExp implements Exp {
    private final Value exp;

    public ValueExp(Value e)
    {
        this.exp = e;
    }

    @Override
    public String toString()
    {
        return this.exp.toString();
    }

    @Override
    public boolean equals(Object another)
    {
        if (another instanceof ValueExp)
            if (this.exp == ((ValueExp)another).exp)
                return true;
        return false;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        return this.exp;
    }

    public Type getType()
    {
        return this.exp.getType();
    }

    @Override
    public Exp deepCopy()
    {
        return new ValueExp(this.exp.deepCopy());
    }

    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return exp.getType();
    }
}