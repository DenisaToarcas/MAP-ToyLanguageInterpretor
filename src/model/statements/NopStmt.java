package model.statements;

import exception.MyException;
import model.prgState.PrgState;

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
}
