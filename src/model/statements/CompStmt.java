package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.statements.IStmt;
import model.adts.MyIStack;
import model.prgState.PrgState;
import model.types.Type;

public class CompStmt implements IStmt {
    private final IStmt first;
    private final IStmt snd;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.snd = second;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + snd.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy()
    {
        return new CompStmt(this.first.deepCopy(), this.snd.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        return snd.typeCheck(first.typeCheck(typeEnv));
    }
}
