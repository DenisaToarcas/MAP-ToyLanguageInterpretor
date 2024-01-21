package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.ValueExp;
import model.prgState.PrgState;
import model.types.Type;
import model.values.IntValue;

public class WaitStmt implements IStmt{
    private final int number;

    public WaitStmt(int number)
    {
        this.number = number;
    }

    @Override
    public String toString()
    {
        return "wait(" + this.number + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if (this.number != 0)
        {
            MyIStack<IStmt> exeStack = state.getStk();
            IStmt newStmt = new CompStmt(new PrintStmt(new ValueExp(new IntValue(this.number))), new WaitStmt(this.number-1));
            exeStack.push(newStmt);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new WaitStmt(this.number);
    }
}
