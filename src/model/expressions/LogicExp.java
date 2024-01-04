package model.expressions;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.types.BoolType;
import model.types.IntType;
import model.types.Type;
import model.values.*;

import java.util.Objects;

public class LogicExp implements Exp{
    private final Exp e1;
    private final Exp e2;
    private final String logicOp;

    public LogicExp(String logicOp, Exp e1, Exp e2)
    {
        this.e1 = e1;
        this.e2 = e2;
        this.logicOp = logicOp;
    }

    @Override
    public String toString()
    {
        return this.e1.toString() + " " + this.logicOp + " " + this.e2.toString();
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value v1, v2;
        v1 = this.e1.eval(tbl, heap);

        if (v1.getType().equals(new BoolType())) {
            v2 = this.e2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) v1;
                BoolValue bool2 = (BoolValue) v2;

                if (Objects.equals(this.logicOp, "or") || Objects.equals(this.logicOp, "OR"))
                    return new BoolValue(bool1.getValue() || bool2.getValue());
                if (Objects.equals(this.logicOp, "and") || Objects.equals(this.logicOp, "AND"))
                    return new BoolValue(bool1.getValue() && bool2.getValue());
                else
                    throw new MyException("logic op is not defined!");

            } else throw new MyException("second expression is not bool!\n");
        }
        else throw new MyException("first expression is not bool!\n");
    }

    @Override
    public Exp deepCopy()
    {
        return new LogicExp(logicOp, this.e1.deepCopy(), this.e2.deepCopy());
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException
    {
        Type typ1, typ2;

        typ1 = this.e1.typeCheck(typeEnv);
        typ2 = this.e2.typeCheck(typeEnv);

        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not a boolean");
        }else
            throw new MyException("first operand is not a boolean");
    }
    }