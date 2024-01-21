package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.types.IntType;
import model.types.Type;
import model.values.Value;

public class MULExp implements Exp{
    private final Exp exp1;
    private final Exp exp2;

    public MULExp(Exp exp1, Exp exp2)
    {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public String toString()
    {
        return "MUL(" + this.exp1.toString() + ", " + this.exp2.toString() + ")";
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Exp newEXp = new ArithExp("-", new ArithExp("*", this.exp1, this.exp2), new ArithExp("+", this.exp1, this.exp2));
        return newEXp.eval(tbl, heap);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = this.exp1.typeCheck(typeEnv);
        Type type2 = this.exp2.typeCheck(typeEnv);

        if (type1.equals(new IntType()) && type2.equals(new IntType()))
            return new IntType();
        else
            throw new MyException("The expressions in the MUL are not IntType!");
    }

    @Override
    public Exp deepCopy() {
        return new MULExp(this.exp1.deepCopy(), this.exp2.deepCopy());
    }
}
