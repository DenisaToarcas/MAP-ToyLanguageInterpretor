package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.Exp;
import model.prgState.PrgState;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.Value;

public class WhileStmt implements IStmt{
    private final Exp exp;
    private final IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt)
    {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public String toString()
    {
        return "while(" + this.exp.toString() + ") " + this.stmt.toString();
    }

    public PrgState execute(PrgState state) throws Exception
    {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIStack<IStmt> exeStk = state.getStk();

        Value val = this.exp.eval(symTbl, state.getHeap());

        if (val.getType().equals(new BoolType()))
        {
            if (val.equals(new BoolValue(true)))
            {
                exeStk.push(this);
                exeStk.push(this.stmt);
            }
        }
        else
            throw new MyException("The expression is not of type boolean!!!");

        return null;
    }

    public IStmt deepCopy()
    {
        return new WhileStmt(this.exp.deepCopy(), this.stmt.deepCopy());
    }
}
