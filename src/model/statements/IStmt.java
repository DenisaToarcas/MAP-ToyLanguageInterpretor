package model.statements;

import exception.MyException;
import model.prgState.PrgState;

public interface IStmt{
    PrgState execute(PrgState state) throws Exception;
    //which is the execution method for a statement.

    IStmt deepCopy();
}