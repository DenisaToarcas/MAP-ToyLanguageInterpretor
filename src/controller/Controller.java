package controller;
import exception.MyException;
import model.prgState.PrgState;
import model.values.Value;
import repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import static model.adts.GarbageCollector.*;

public class Controller {
    private final IRepository repo;
    private boolean flag;

    public Controller(IRepository repo)
    {
        this.repo = repo;
        this.flag = false;
    }

    public void allStep() throws MyException, Exception {
        //StringBuilder PrgStates = new StringBuilder();

        PrgState state = this.repo.getCrtState();
        //PrgStates.append(state.toString());
        //PrgStates.append("\n");

        repo.logPrgStateExec();

        while (!state.getStk().isEmpty())
        {
            state.oneStep();
            if (this.flag)
            {
                repo.logPrgStateExec();
                //PrgStates.append(state.toString());
                //PrgStates.append("\n");

                state.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(
                                getAddrFromBoth(getAddrFromSymTable(state.getSymTable().getContent().values()), getAddrFromHeap(state.getHeap().getContent().values())),
                                state.getHeap().getContent()
                        )
                );

                repo.logPrgStateExec();
            }
        }

        if (!this.flag)
        {
            repo.logPrgStateExec();
            //PrgStates.append(state.toString());
            //PrgStates.append("\n");

            state.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(
                    getAddrFromBoth(getAddrFromSymTable(state.getSymTable().getContent().values()), getAddrFromHeap(state.getHeap().getContent().values())),
                    state.getHeap().getContent()
                    )
            );

            repo.logPrgStateExec();
        }

        this.flag = false;

        //return PrgStates.toString();
    }

    public void setFlag()
    {
        this.flag = true;
    }
}
