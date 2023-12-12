package repository;

import exception.MyException;
import model.prgState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository{
    private final PrgState prgState;
    private final String logFile;

    public Repository(PrgState state, String logFile)
    {
        this.prgState = state;
        this.logFile = logFile;
    }

    @Override
    public PrgState getCrtState() throws MyException {
        return this.prgState;
    }

    @Override
    public String toString()
    {
        return this.prgState.toString();
    }

    @Override
    public void logPrgStateExec() throws MyException, IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.logFile, true)));
        String result = this.prgState.toString();

        writer.write(result);
        writer.close();
    }
}
