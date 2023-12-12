package repository;
import exception.MyException;
import model.prgState.*;

import java.io.IOException;
import java.util.ArrayList;

public interface IRepository {
    PrgState getCrtState() throws MyException;
    void logPrgStateExec() throws MyException, IOException;
}
