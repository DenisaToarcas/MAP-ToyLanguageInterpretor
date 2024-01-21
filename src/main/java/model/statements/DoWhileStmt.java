package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.Exp;
import model.prgState.PrgState;
import model.types.BoolType;
import model.types.Type;

public class DoWhileStmt implements IStmt{
    private final IStmt stmt;
    private final Exp exp;

    public DoWhileStmt(IStmt stmt, Exp exp)
    {
        this.stmt = stmt;
        this.exp = exp;
    }

    @Override
    public String toString()
    {
        return "do {" + this.stmt.toString() + "} while(" + this.exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> exeStack = state.getStk();
        IStmt newStmt = new CompStmt(this.stmt, new WhileStmt(this.exp, this.stmt));
        exeStack.push(newStmt);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = this.exp.typeCheck(typeEnv);
        if (type.equals(new BoolType()))
        {
            this.stmt.typeCheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("The expression in the DoWhile is not BoolType!");
    }

    @Override
    public IStmt deepCopy() {
        return new DoWhileStmt(this.stmt.deepCopy(), this.exp.deepCopy());
    }
}
