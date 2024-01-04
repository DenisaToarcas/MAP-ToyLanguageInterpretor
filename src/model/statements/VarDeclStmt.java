package model.statements;

import exception.MyException;
import model.adts.MyIDictionary;
import model.adts.MyIFileTable;
import model.prgState.PrgState;
import model.statements.IStmt;
import model.types.*;
import model.values.Value;

import java.io.BufferedReader;

public class VarDeclStmt implements IStmt {
    public String name;
    public Type typ;
    public Value defaultValue;

    public VarDeclStmt(String name, Type typ)
    {
        this.name = name;
        this.typ = typ;
        this.defaultValue = this.typ.defaultValue();
    }

    @Override
    public String toString()
    {
        return this.typ.toString() + " " + this.name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIDictionary<String, Value> SymTbl = state.getSymTable();
        MyIFileTable<String, BufferedReader> FileTbl = state.getFileTable();

        if (SymTbl.isDefined(this.name))
            throw new MyException("The variable " + this.name + " is already declared!");
        SymTbl.update(this.name, this.defaultValue);

        return null;
    }

    @Override
    public IStmt deepCopy(){
        return new VarDeclStmt(this.name, this.typ.deepCopy());
    }

    @Override
    public MyIDictionary<String,Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException{
        typeEnv.add(name,typ);

        return typeEnv;
    }


}