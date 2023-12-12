package view;

import exception.MyException;
import model.adts.*;
import model.expressions.*;
import model.values.*;
import model.types.*;
import model.prgState.PrgState;
import model.statements.*;
import repository.*;
import controller.*;

import java.io.BufferedReader;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

        MyIStack<IStmt> exeStack1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl1 = new MyDictionary<String, Value>();
        MyIList<Value> Out1 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> FileTable1 = new FileTable();
        MyIHeap<Integer, Value> Heap1 = new Heap();

        PrgState prgState1 = new PrgState(exeStack1, SymTbl1, Out1, FileTable1, Heap1, ex1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp("+", new ValueExp(new IntValue(2)), new
                                ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        MyIStack<IStmt> exeStack2 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl2 = new MyDictionary<String, Value>();
        MyIList<Value> Out2 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> FileTable2 = new FileTable();
        MyIHeap<Integer, Value> Heap2 = new Heap();

        PrgState prgState2 = new PrgState(exeStack2, SymTbl2, Out2, FileTable2, Heap2, ex2);

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));


        MyIStack<IStmt> exeStack3 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl3 = new MyDictionary<String, Value>();
        MyIList<Value> Out3 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> FileTable3 = new FileTable();
        MyIHeap<Integer, Value> Heap3 = new Heap();

        PrgState prgState3 = new PrgState(exeStack3, SymTbl3, Out3, FileTable3, Heap3, ex3);

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))));

        MyIStack<IStmt> exeStack4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl4 = new MyDictionary<String, Value>();
        MyIList<Value> Out4 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> FileTable4 = new FileTable();
        MyIHeap<Integer, Value> Heap4 = new Heap();

        PrgState prgState4 = new PrgState(exeStack4, SymTbl4, Out4, FileTable4, Heap4, ex4);

        IStmt ex5 = new PrintStmt(new RelationalExp("==", new ValueExp(new IntValue(6)), new ValueExp(new IntValue(7))));
        MyIStack<IStmt> exeStack5 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl5 = new MyDictionary<String, Value>();
        MyIList<Value> Out5 = new MyList<Value>();
        MyIFileTable<String, BufferedReader> FileTable5 = new FileTable();
        MyIHeap<Integer, Value> Heap5 = new Heap();

        PrgState prgState5 = new PrgState(exeStack5, SymTbl5, Out5, FileTable5, Heap5, ex5);



        System.out.println("Welcome!");

        while (true) {
            try {
                System.out.println("Type which of the PrgStates to be executed:");
                Scanner sc = new Scanner(System.in);
                String option_chosen = sc.nextLine();
                int index = Integer.parseInt(option_chosen);

                System.out.println("Write the name of the logFile:");
                String logFile = sc.nextLine();

                System.out.println("Do you want to show the ExeStack, SymTable and Out?\nWrite yes to set the flag to true.");
                String option_flag = sc.nextLine();
                if (Objects.equals(option_flag, "yes"))
                    switch (index) {
                        case 1:
                            IRepository repo1 = new Repository(prgState1, logFile);
                            Controller controller1 = new Controller(repo1);
                            controller1.setFlag();
                            controller1.allStep();
                            //System.out.println(controller1.allStep());
                            break;

                        case 2:
                            IRepository repo2 = new Repository(prgState2, logFile);
                            Controller controller2 = new Controller(repo2);
                            controller2.setFlag();
                            controller2.allStep();
                            //System.out.println(controller2.allStep());
                            break;

                        case 3:
                            IRepository repo3 = new Repository(prgState3, logFile);
                            Controller controller3 = new Controller(repo3);
                            controller3.setFlag();
                            controller3.allStep();
                            //System.out.println(controller3.allStep());
                            break;

                        case 4:
                            IRepository repo4 = new Repository(prgState4, logFile);
                            Controller controller4 = new Controller(repo4);
                            controller4.setFlag();
                            controller4.allStep();
                            break;

                        case 5:
                            IRepository repo5 = new Repository(prgState5, logFile);
                            Controller controller5 = new Controller(repo5);
                            controller5.setFlag();
                            controller5.allStep();
                            break;

                    }
                else
                {
                    switch (index) {
                        case 1:
                            IRepository repo1 = new Repository(prgState1, logFile);
                            Controller controller1 = new Controller(repo1);
                            controller1.allStep();
                            //System.out.println(controller1.allStep());
                            break;

                        case 2:
                            IRepository repo2 = new Repository(prgState2, logFile);
                            Controller controller2 = new Controller(repo2);
                            controller2.allStep();
                            //System.out.println(controller2.allStep());
                            break;

                        case 3:
                            IRepository repo3 = new Repository(prgState3, logFile);
                            Controller controller3 = new Controller(repo3);
                            controller3.allStep();
                            //System.out.println(controller3.allStep());
                            break;

                        case 4:
                            IRepository repo4 = new Repository(prgState4, logFile);
                            Controller controller4 = new Controller(repo4);
                            controller4.allStep();
                            break;

                        case 5:
                            IRepository repo5 = new Repository(prgState5, logFile);
                            Controller controller5 = new Controller(repo5);
                            controller5.setFlag();
                            controller5.allStep();
                            break;

                    }
                }

                System.out.println("Do you want to end the program?\n Type yes if so OR whatever to continue:");
                String option = sc.nextLine();
                if (Objects.equals(option, "yes"))
                    break;

            } catch (MyException exception) {
                System.out.println(exception.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}