package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.Exp;
import model.expressions.RelationalExp;
import model.prgState.PrgState;
import model.types.Type;

public class SwitchStmt implements IStmt{
    private final Exp exp;
    private final Exp exp1;
    private final IStmt stmt1;
    private final Exp exp2;
    private final IStmt stmt2;
    private final IStmt stmt3;

    public SwitchStmt(Exp exp, Exp exp1, IStmt stmt1, Exp exp2, IStmt stmt2, IStmt stmt3)
    {
        this.exp = exp;
        this.exp1 = exp1;
        this.stmt1 = stmt1;
        this.exp2 = exp2;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public String toString()
    {
        return "switch(" + this.exp.toString() + ") (case " + this.exp1.toString() + ": " + this.stmt1.toString() + ") (case " + this.exp2.toString() + ": " + this.stmt2.toString() + ") (default: " + this.stmt3.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> exeStack = state.getStk();
        IStmt newStmt = new IfStmt(new RelationalExp("==", this.exp, this.exp1), stmt1, new IfStmt(new RelationalExp("==", this.exp, this.exp2), stmt2, stmt3));
        exeStack.push(newStmt);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = this.exp.typeCheck(typeEnv);
        Type typeExp1 = this.exp1.typeCheck(typeEnv);
        Type typeExp2 = this.exp2.typeCheck(typeEnv);

        if (typeExp1.equals(typeExp) && typeExp2.equals(typeExp))
        {
            this.stmt1.typeCheck(typeEnv);
            this.stmt2.typeCheck(typeEnv);
            this.stmt3.typeCheck(typeEnv);

            return typeEnv;
        }
        else
            throw new MyException("The expressions do not have the same types in Switch Stmt!");
    }

    @Override
    public IStmt deepCopy() {
        return new SwitchStmt(this.exp.deepCopy(), this.exp1.deepCopy(), this.stmt1.deepCopy(), this.exp2.deepCopy(), this.stmt2.deepCopy(), this.stmt3.deepCopy());
    }
}
