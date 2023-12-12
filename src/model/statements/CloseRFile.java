package model.statements;

import exception.MyException;
import model.adts.MyIFileTable;
import model.expressions.Exp;
import model.prgState.PrgState;

import java.io.BufferedReader;

public class CloseRFile implements IStmt{
    private final Exp exp;

    public CloseRFile(Exp exp)
    {
        this.exp = exp;
    }

    @Override
    public String toString()
    {
        return "CloseRFile: " + this.exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, Exception
    {
        MyIFileTable<String, BufferedReader> FileTbl = state.getFileTable();

        String fileName = state.getFileName(this.exp);

        if (FileTbl.isDefined(fileName))
        {
            FileTbl.close(fileName);
        }
        else
            throw new MyException("The filename: " + fileName  + " was not defined in the FileTable!!!");

        return state;
    }

    @Override
    public IStmt deepCopy()
    {
        return new CloseRFile(this.exp.deepCopy());
    }
}
