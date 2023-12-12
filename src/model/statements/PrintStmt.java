package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIList;
import model.adts.MyIStack;
import model.expressions.Exp;
import model.prgState.PrgState;
import model.statements.IStmt;
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
        return state;
    }

    @Override
    public IStmt deepCopy()
    {
        return new PrintStmt(exp.deepCopy());
    }
}
