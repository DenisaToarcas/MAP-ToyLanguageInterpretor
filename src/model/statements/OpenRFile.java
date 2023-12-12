package model.statements;

import exception.MyException;
import model.adts.MyIFileTable;
import model.expressions.*;
import model.prgState.PrgState;

import java.io.BufferedReader;

public class OpenRFile implements IStmt{
    private final Exp exp;

    public OpenRFile(Exp exp)
    {
        this.exp = exp;
    }

    @Override
    public String toString()
    {
        return "OpenRFile: " + this.exp.toString();
    }
    public PrgState execute(PrgState state) throws MyException, Exception
    {
        MyIFileTable<String, BufferedReader> FileTbl = state.getFileTable();

        String fileName = state.getFileName(this.exp);

        if (!FileTbl.isDefined(fileName))
        {
            FileTbl.open(fileName);
        }
        else
            throw new MyException("The filename: " + fileName  + " was already defined in the FileTable!!!");

        return state;
    }

    public IStmt deepCopy()
    {
        return new OpenRFile(this.exp.deepCopy());
    }
}
