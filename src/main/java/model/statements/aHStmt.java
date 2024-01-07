package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIHeap;
import model.expressions.Exp;
import model.prgState.PrgState;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;

public class aHStmt implements IStmt{
    private final String varName;
    private final Exp expression;

    public aHStmt(String varName, Exp expression)
    {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public String toString() {

        return "aH(" + varName + ", " + expression.toString() + ")";

    }

    public synchronized PrgState execute(PrgState state) throws Exception
    {
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIHeap<Integer, Value> Heap = state.getHeap();

        if (symTbl.isDefined(varName)) {
            if (symTbl.lookup(varName).getType() instanceof RefType)
            {
                Value expValue = this.expression.eval(symTbl, Heap);
                Type varType = ((RefType) symTbl.lookup(varName).getType()).getInner();

                if (expValue.getType().equals(varType)) {
                    int address = Heap.IncAndGet();
                    Heap.update(address, expValue);
                    RefValue newRefValue = ((RefValue) symTbl.lookup(varName)).setAddress(address);
                    symTbl.update(varName, newRefValue);
                }
                else
                    throw new MyException("Expression and variable are of different types!!!");
            }
            else
                throw new MyException("The type is not RefType!!!");
        }else
            throw new MyException("The used variable " + varName + " was not declared before!!\n");

        return null;
    }

    @Override
    public IStmt deepCopy()
    {
        return new aHStmt(varName, this.expression.deepCopy());
    }

    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typevar = typeEnv.lookup(varName);
        Type typexp = expression.typeCheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("aH stmt: right hand side and left hand side have different types ");
    }
}
