package model.prgState;
import exception.MyException;
import model.statements.*;
import model.adts.*;
import model.types.StringType;
import model.values.*;
import model.expressions.*;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static model.adts.GarbageCollector.*;

public class PrgState{
    private final MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private final MyIList<Value> out;
    private final MyIFileTable<String, BufferedReader> fileTable;

    private final MyIHeap<Integer, Value> heap;
    private final IStmt originalProgram; //optional field, but good to have

    private final int prgStateID;
    private static AtomicInteger ID = new AtomicInteger(0);
    //In Java, the AtomicInteger class is part of the java.util.concurrent.atomic package
    //and provides atomic operations on integers.
    //This means that operations performed on AtomicInteger instances are guaranteed
    // to be executed atomically, without the need for explicit synchronization.

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value>
            ot, MyIFileTable<String, BufferedReader> fileTable, MyIHeap<Integer, Value> heap, IStmt prg){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        this.fileTable = fileTable;
        this.heap = heap;
        this.prgStateID = ID.incrementAndGet();
        //The incrementAndGet() method atomically increments the current value
        // of the AtomicInteger by one and returns the updated value.

        originalProgram = prg.deepCopy(); //recreate the entire original prg
        stk.push(originalProgram);
    }

    public int getPrgStateID()
    {
        return this.prgStateID;
    }

    @Override
    public String toString()
    {
        return "The program state ID is:" + this.prgStateID + "\n" +
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
        if (this.exeStack.isEmpty())
            throw new MyException("The ExeStack of the PrgState is empty!");

        IStmt crtStmt = this.exeStack.pop();

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

    public boolean isNotCompleted()
    {
        return !exeStack.isEmpty();
    }
}