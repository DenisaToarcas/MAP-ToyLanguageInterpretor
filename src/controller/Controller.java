package controller;
import exception.MyException;
import model.prgState.PrgState;
import model.values.RefValue;
import model.values.Value;
import repository.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static model.adts.GarbageCollector.*;

public class Controller {
    private final IRepository repo;
    private ExecutorService executor;
    private boolean flag;

    public Controller(IRepository repo)
    {
        this.repo = repo;
        this.flag = false;
    }

    public ArrayList<PrgState> removeCompletedPrg(ArrayList<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void oneStepForAllPrg(ArrayList<PrgState> prgList) throws Exception {
        prgList.forEach(prgState -> {
            try {
                repo.logPrgStateExec(prgState);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        //RUN concurrently one step for each of the existing PrgStates
        //-----------------------------------------------------------------------
        //prepare the list of callables

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) p::oneStep)
                .collect(Collectors.toList());

        ArrayList<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));

        //add the new created threads to the list of existing threads
        prgList.addAll(newPrgList);
        //------------------------------------------------------------------------------

        List<Integer> addrFromHeap = getAddrFromHeap(prgList.getFirst().getHeap().getContent().values());
        List<Integer> addrFromSymTbls = new ArrayList<>();
        List<Integer> addresses = new ArrayList<>();

        for (int index = 0; index < prgList.size(); index++)
        {
            addresses = getAddrFromSymTable(prgList.get(index).getSymTable().getContent().values());
            addrFromSymTbls.addAll(addresses);
        }

        addrFromSymTbls.addAll(addresses);

        prgList.getFirst().getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(getAddrFromBoth(addrFromSymTbls, addrFromHeap), prgList.getFirst().getHeap().getContent()));

        //after the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        //Save the current programs in the repository
        repo.setPrgList(prgList);
    }

    public void allStep() throws Exception {
        executor = Executors.newFixedThreadPool(2);

        //remove the completed programs
        ArrayList<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        while(!prgList.isEmpty()){

            oneStepForAllPrg(prgList);

            //remove the completed programs
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its ArrayList<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repo.setPrgList(prgList);
    }

    //public static List<Collection<Value>> getAddrFromAllSymTbl(ArrayList<PrgState> prgStates)
//    {
//        return prgStates.stream().map(programState -> programState.getSymTable().getContent().values()).collect(Collectors.toList());
//    }

    public void setFlag()
    {
        this.flag = true;
    }
}
