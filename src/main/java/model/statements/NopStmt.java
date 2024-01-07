package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.prgState.PrgState;
import model.types.Type;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
   return null;
 }
    @Override
    public String toString()
    {
        return "";
    }

    @Override
    public IStmt deepCopy() {
   return new NopStmt();
 }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
