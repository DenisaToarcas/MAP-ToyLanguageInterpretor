package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.prgState.PrgState;
import model.types.Type;

public interface IStmt{
    PrgState execute(PrgState state) throws Exception;
    //which is the execution method for a statement.

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException;

    IStmt deepCopy();
}