package repository;

import exception.MyException;
import model.prgState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private ArrayList<PrgState> listPrgStates = new ArrayList<>();
    private final String logFile;

    public Repository(PrgState state, String logFile)
    {
        addPrgState(state);
        this.logFile = logFile;
    }

    @Override
    public void addPrgState(PrgState state)
    {
        this.listPrgStates.add(state);
    }

    @Override
    public ArrayList<PrgState> getPrgList()
    {
        return this.listPrgStates;
    }

    @Override
    public void setPrgList(ArrayList<PrgState> list)
    {
        this.listPrgStates = list;
    }

    @Override
    public String toString()
    {
        return this.listPrgStates.toString();
    }

    @Override
    public void logPrgStateExec(PrgState prgState) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.logFile, true)));
        String result = prgState.toString();

        writer.write(result);
        writer.close();
    }
}
