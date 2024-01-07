package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.types.Type;
import model.values.Value;

import java.util.Objects;

public class VarExp implements Exp{
    private final String id;

    public VarExp(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return this.id;
    }

    @Override
    public boolean equals (Object another)
    {
        if (another instanceof VarExp)
            if (Objects.equals(this.id, ((VarExp) another).id))
                return true;
        return false;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws MyException
    {
        return tbl.lookup(id);
    }

    @Override
    public Exp deepCopy()
    {
        return new VarExp(this.id);
    }

    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return typeEnv.lookup(id);
    }
}
