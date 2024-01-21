package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.expressions.Exp;
import model.expressions.RelationalExp;
import model.expressions.VarExp;
import model.prgState.PrgState;
import model.types.IntType;
import model.types.Type;

public class ForStmt implements IStmt{
    private final String id;
    private final Exp exp1;
    private final Exp exp2;
    private final Exp exp3;
    private final IStmt stmt;

    public ForStmt(String id, Exp exp1, Exp exp2, Exp exp3, IStmt stmt)
    {
        this.id = id;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt = stmt;
    }

    @Override
    public String toString()
    {
        return "for (" + this.id + "=" + this.exp1.toString() + ";" + this.id + "<" + this.exp2.toString() + ";" + this.id + "=" + this.exp3.toString() + ") " + this.stmt.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> exeStack = state.getStk();
        IStmt newStmt = new CompStmt(new VarDeclStmt(this.id, new IntType()),
                new CompStmt(new AssignStmt(this.id, this.exp1),
                        new WhileStmt(new RelationalExp("<", new VarExp(this.id), this.exp2),
                                new CompStmt(this.stmt, new AssignStmt(this.id, this.exp3)))));
        exeStack.push(newStmt);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(this.id, new IntType());
        Type typeExp1 = this.exp1.typeCheck(typeEnv);
        Type typeExp2 = this.exp2.typeCheck(typeEnv);
        Type typeExp3 = this.exp3.typeCheck(typeEnv);

        if (typeExp1.equals(new IntType()) && typeExp2.equals(new IntType()) && typeExp3.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("The types of the expressions if for are not all IntType!");
    }

    @Override
    public IStmt deepCopy() {
        return new ForStmt(this.id, this.exp1.deepCopy(), this.exp2.deepCopy(), this.exp3.deepCopy(), this.stmt.deepCopy());
    }
}
