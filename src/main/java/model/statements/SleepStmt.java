package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.prgState.PrgState;
import model.types.Type;

public class SleepStmt implements IStmt{
    private final int number;

    public SleepStmt(int number)
    {
        this.number = number;
    }

    @Override
    public String toString()
    {
        return "sleep(" + this.number + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        if (this.number != 0)
        {
            MyIStack<IStmt> exeStack = state.getStk();
            IStmt newStmt = new SleepStmt(this.number-1);
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
        return new SleepStmt(this.number);
    }
}
