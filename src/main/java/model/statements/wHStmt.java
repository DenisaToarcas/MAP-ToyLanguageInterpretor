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

public class wHStmt implements IStmt{
    private final String varName;

    private final Exp exp;

    public wHStmt(String varName, Exp exp)
    {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public String toString() {

        return "wH(" + varName + ", " + exp.toString() + ")";

    }

    public synchronized PrgState execute(PrgState state) throws Exception
    {
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        MyIHeap<Integer, Value> Heap = state.getHeap();

        if (symTbl.isDefined(varName)) {
            if (symTbl.lookup(varName).getType() instanceof RefType)
            {
                RefValue value = (RefValue) symTbl.getValue(varName);
                int address = value.getAddress();

                if (Heap.isDefined(address))
                {
                    Value expEval = this.exp.eval(symTbl, Heap);
                    if (expEval.getType().equals(Heap.getValue(address).getType()))
                    {
                        Heap.update(address, expEval);
                    }
                    else
                        throw new MyException("The types of the exp and the locationType are not the same!!");
                }
                else
                    throw new MyException("The address is not defined in the Heap!!");
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
        return new wHStmt(this.varName, this.exp.deepCopy());
    }

    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        Type typevar = typeEnv.lookup(varName);
        Type typexp = exp.typeCheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("wH stmt: right hand side and left hand side have different types ");
    }
}
