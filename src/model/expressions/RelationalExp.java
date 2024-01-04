package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

import java.util.Objects;

public class RelationalExp implements Exp{
    private final Exp exp1;
    private final Exp exp2;
    private final String relation;

    public RelationalExp(String relation, Exp exp1, Exp exp2)
    {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.relation = relation;
    }

    @Override
    public String toString()
    {
        return this.exp1.toString() + " " + this.relation + " " + this.exp2.toString();
    }
    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws MyException
    {
        Value v1,v2;
        v1= exp1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = exp2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if (Objects.equals(this.relation, "<")) return new BoolValue(n1 < n2);
                if (Objects.equals(this.relation, "<=")) return new BoolValue(n1 <= n2);
                if (Objects.equals(this.relation, "==")) return new BoolValue(n1 == n2);
                if (Objects.equals(this.relation, "!=")) return new BoolValue(n1 != n2);
                if (Objects.equals(this.relation, ">")) return new BoolValue(n1 > n2);
                if (Objects.equals(this.relation, ">=")) return new BoolValue(n1 >= n2);
                else
                    throw new MyException("relational op not defined!");

            }else
                throw new MyException("second operand is not an integer\n");
        }else
            throw new MyException("first operand is not an integer\n");
    }

    @Override
    public Exp deepCopy()
    {
        return new RelationalExp(this.relation, this.exp1.deepCopy(), this.exp2.deepCopy());
    }

    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=exp1.typeCheck(typeEnv);
        typ2=exp2.typeCheck(typeEnv);

        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
}
