package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.*;
import model.prgState.PrgState;
import model.types.BoolType;
import model.types.Type;
import model.values.*;

public class IfStmt implements IStmt {
    private final Exp exp;
    private final IStmt thenS;
    private final IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS)
    {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString(){
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString()
            +")ELSE("+elseS.toString()+"))";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> SymTbl = state.getSymTable();

        if (!(this.exp.eval(SymTbl, state.getHeap()).getType()).equals(new BoolType()))
            throw new MyException("The conditional expression is not a boolean!");
        else {
            if (this.exp.eval(SymTbl, state.getHeap()).equals(new BoolValue(true)))
                stk.push(this.thenS);
            else
                stk.push(this.elseS);
        }
        return null;
    }

    @Override
    public IStmt deepCopy()
    {
        return new IfStmt(this.exp.deepCopy(), this.thenS.deepCopy(), this.elseS.deepCopy());
    }

    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{

        Type typexp=exp.typeCheck(typeEnv);

        if (typexp.equals(new BoolType())) {
            thenS.typeCheck(typeEnv);
            elseS.typeCheck(typeEnv);

            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

}


