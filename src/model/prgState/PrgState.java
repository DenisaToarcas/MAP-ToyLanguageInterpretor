package model.prgState;
import exception.MyException;
import model.statements.*;
import model.adts.*;
import model.types.StringType;
import model.values.*;
import model.expressions.*;

import java.io.BufferedReader;

public class PrgState{
    private final MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private final MyIList<Value> out;
    private final MyIFileTable<String, BufferedReader> fileTable;

    private final MyIHeap<Integer, Value> heap;
    private final IStmt originalProgram; //optional field, but good to have

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value>
            ot, MyIFileTable<String, BufferedReader> fileTable, MyIHeap<Integer, Value> heap, IStmt prg){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        this.fileTable = fileTable;
        this.heap = heap;
        originalProgram = prg.deepCopy(); //recreate the entire original prg
        stk.push(originalProgram);
    }

    @Override
    public String toString()
    {
        return "The program state:\n" +
                "The ExeStack:\n" + this.exeStack.toString() + "\n" +
                "The SymTable:\n" + this.symTable.toString() + "\n" +
                "The Heap:\n" + this.heap.toString() + "\n" +
                "The FileTable:\n" + this.fileTable.toString() + "\n" +
                "The Out list:\n" + this.out.toString() + "\n" +
                "\n";
    }

    public MyIStack<IStmt> getStk() {
        return this.exeStack;
    }

    public MyIList<Value> getOut()
    {
        return this.out;
    }

    public MyIDictionary<String, Value> getSymTable()
    {
        return this.symTable;
    }

    public MyIFileTable<String, BufferedReader> getFileTable()
    {
        return this.fileTable;
    }

    public MyIHeap<Integer, Value> getHeap()
    {
        return this.heap;
    }

    public IStmt getOriginalProgram()
    {
        return this.originalProgram;
    }

    public String getFileName(Exp exp) throws MyException
    {
        Value val = exp.eval(symTable, heap);
        if (val.getType().equals(new StringType()))
        {
            return ((StringValue) val).getValue();
        }
        else
            throw new MyException("The type of the fileName is not StringType!");
    }

    public PrgState oneStep() throws MyException, Exception
    {
        MyIStack<IStmt> stk = this.getStk();

        if (stk.isEmpty())
            throw new MyException("The ExeStack of the PrgState is empty!");

        IStmt crtStmt = stk.pop();
        return crtStmt.execute(this);
    }

    @Override
    public boolean equals(Object another)
    {
        if (!(another instanceof PrgState))
            return false;
        else
            if (this.exeStack.equals(((PrgState) another).exeStack) && this.symTable.equals(((PrgState) another).symTable) && this.out.equals(((PrgState) another).out) && this.fileTable.equals(((PrgState) another).fileTable))
                return true;
            return false;
    }
}