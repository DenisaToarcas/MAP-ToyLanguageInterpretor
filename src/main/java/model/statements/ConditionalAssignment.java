package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIStack;
import model.prgState.PrgState;
import model.types.BoolType;
import model.types.Type;
import model.expressions.*;

public class ConditionalAssignment implements IStmt{
    private final String id;
    private final Exp exp1;
    private final Exp exp2;
    private final Exp exp3;

    public ConditionalAssignment(String id, Exp exp1, Exp exp2, Exp exp3)
    {
        this.id = id;
        this.exp1 =  exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public String toString()
    {
        return id + "=(" + this.exp1.toString() + ")?" + this.exp2.toString() + ":" + this.exp3.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> exeStack = state.getStk();
        IStmt newIf = new IfStmt(this.exp1, new AssignStmt(this.id, this.exp2), new AssignStmt(this.id, this.exp3));
        exeStack.push(newIf);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type variableType = typeEnv.lookup(id);
        Type typeExp1 = this.exp1.typeCheck(typeEnv);
        Type typeExp2  = this.exp2.typeCheck(typeEnv);
        Type typeExp3 = this.exp3.typeCheck(typeEnv);

        if (typeExp1.equals(new BoolType()) && typeExp2.equals(variableType) && typeExp3.equals(variableType))
            return typeEnv;
        else
            throw new MyException("The types of the expressions are not correct in Conditional Assignment!");
    }

    @Override
    public IStmt deepCopy() {
        return new ConditionalAssignment(this.id, this.exp1.deepCopy(), this.exp2.deepCopy(), this.exp3.deepCopy());
    }
}
