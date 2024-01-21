package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.statements.IStmt;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class NotExp implements Exp{
    private final Exp exp;

    public NotExp(Exp exp)
    {
        this.exp = exp;
    }

    @Override
    public String toString()
    {
        return "!" + this.exp.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        BoolValue value = (BoolValue) this.exp.eval(tbl, heap);
        if (value.getValue())
            return new BoolValue(false);
        else
            return new BoolValue(true);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return this.exp.typeCheck(typeEnv);
    }

    @Override
    public Exp deepCopy() {
        return new NotExp(this.exp.deepCopy());
    }
}
