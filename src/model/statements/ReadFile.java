package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIFileTable;
import model.expressions.Exp;
import model.prgState.PrgState;
import model.types.IntType;
import model.types.StringType;
import model.types.Type;
import model.values.IntValue;
import model.values.Value;

import java.io.BufferedReader;

public class ReadFile implements IStmt{
    private final Exp exp;
    private final String var_name;

    public ReadFile(Exp exp, String var_name)
    {
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public String toString()
    {
        return "ReadFile: " + this.var_name + " = read(" + this.exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception
    {
        MyIDictionary<String, Value> SymTbl = state.getSymTable();
        MyIFileTable<String, BufferedReader> FileTbl = state.getFileTable();

        if (SymTbl.isDefined(this.var_name))
        {
            if (SymTbl.lookup(var_name).getType().equals(new IntType()))
            {
                String fileName = state.getFileName(exp);
                if (FileTbl.isDefined(fileName))
                {
                    IntValue val = FileTbl.read(fileName);
                    SymTbl.update(var_name, val);
                }
                else
                    throw new MyException("The filename: " + fileName  + " was not defined in the FileTable!!!");
            }
            else
                throw new MyException("The type of the variable " + var_name + " is not INT!!!!");
        }
        else
            throw new MyException("The variable name " + var_name + " was not defined before!!!");

        return null;
    }

    @Override
    public IStmt deepCopy()
    {
        return new ReadFile(this.exp.deepCopy(), var_name);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException
    {
        Type type = this.exp.typeCheck(typeEnv);

        if (type.equals(new StringType()))
            return typeEnv;
        else throw new MyException("Read file argument is not of type String!!");
    }
}
