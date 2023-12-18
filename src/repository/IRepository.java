package repository;
import exception.MyException;
import model.prgState.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IRepository {
    void addPrgState(PrgState state);
    ArrayList<PrgState> getPrgList();
    void setPrgList(ArrayList<PrgState> list);
    void logPrgStateExec(PrgState prgState) throws MyException, IOException;
}
