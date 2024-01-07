package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.types.IntType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

import java.util.Objects;

public class ArithExp implements Exp{
    private final Exp e1;
    private final Exp e2;
    private final String sign;

    public ArithExp(String sign, Exp e1, Exp e2)
    {
        this.e1 = e1;
        this.e2 = e2;
        this.sign = sign;
    }

    @Override
    public String toString()
    {
        return this.e1.toString() + " " + this.sign + " " + this.e2.toString();
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws MyException{
        Value v1,v2;
        v1= e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                if (Objects.equals(this.sign, "+")) return new IntValue(n1+n2);
                if (Objects.equals(this.sign, "-")) return new IntValue(n1-n2);
                if(Objects.equals(this.sign, "*")) return new IntValue(n1*n2);
                if(Objects.equals(this.sign, "/")) {
                    if (n2 == 0) throw new MyException("division by zero\n");
                    else return new IntValue(n1 / n2);
                }
                else
                    throw new MyException("arithmetic op not defined!");

            }else
                throw new MyException("second operand is not an integer\n");
        }else
            throw new MyException("first operand is not an integer\n");
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(this.sign, this.e1.deepCopy(), this.e2.deepCopy());
    }

    @Override
    public Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typ1, typ2;
        typ1=e1.typeCheck(typeEnv);
        typ2=e2.typeCheck(typeEnv);

        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
}
