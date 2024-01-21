package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.Exp;
import model.expressions.NotExp;
import model.prgState.PrgState;
import model.types.BoolType;
import model.types.Type;

public class RepeatUntil implements IStmt{
    private final IStmt stmt1;
    private final Exp exp2;

    public RepeatUntil(IStmt stmt1, Exp exp2)
    {
        this.stmt1 = stmt1;
        this.exp2 = exp2;
    }

    @Override
    public String toString()
    {
        return "repeat " + this.stmt1.toString() + " until " + this.exp2.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> exeStack = state.getStk();
        IStmt newStmt = new CompStmt(this.stmt1, new WhileStmt(new NotExp(this.exp2), this.stmt1));
        exeStack.push(newStmt);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = this.exp2.typeCheck(typeEnv);
        if (type.equals(new BoolType()))
        {
            this.stmt1.typeCheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("The type of the expression is not BoolType in RepeatUntil!");
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatUntil(this.stmt1.deepCopy(), this.exp2.deepCopy());
    }
}
