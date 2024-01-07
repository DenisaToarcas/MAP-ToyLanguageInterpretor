package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIFileTable;
import model.expressions.Exp;
import model.prgState.PrgState;
import model.types.StringType;
import model.types.Type;

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

        return null;
    }

    @Override
    public IStmt deepCopy()
    {
        return new OpenRFile(this.exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException
    {
        Type type = this.exp.typeCheck(typeEnv);

        if (type.equals(new StringType()))
            return typeEnv;
        else throw new MyException("Open file argument is not of type String!!");
    }
}
