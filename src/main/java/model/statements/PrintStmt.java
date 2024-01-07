package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIList;
import model.expressions.Exp;
import model.prgState.PrgState;
import model.types.Type;
import model.values.Value;

public class PrintStmt implements IStmt {
    private final Exp exp;

    public PrintStmt(Exp exp)
    {
        this.exp = exp;
    }

    @Override
    public String toString(){
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> list = state.getOut();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        list.add(this.exp.eval(symTbl, state.getHeap()));
        return null;
    }

    @Override
    public IStmt deepCopy()
    {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        exp.typeCheck(typeEnv);

        return typeEnv;
    }
}
