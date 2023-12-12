package view;

import controller.Controller;
import exception.MyException;
import model.adts.*;
import model.expressions.*;
import model.prgState.PrgState;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.util.Scanner;

public class Interpretor {
    public static void main(String[] args) {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

        MyIStack<IStmt> exeStack1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl1 = new MyDictionary<String, Value>();
        MyIList<Value> Out1 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap1 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable1 = new FileTable();

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
        MyIHeap<Integer, Value> Heap2 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable2 = new FileTable();

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
        MyIHeap<Integer, Value> Heap3 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable3 = new FileTable();

        PrgState prgState3 = new PrgState(exeStack3, SymTbl3, Out3, FileTable3, Heap3, ex3);

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))));

        MyIStack<IStmt> exeStack4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl4 = new MyDictionary<String, Value>();
        MyIList<Value> Out4 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap4 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable4 = new FileTable();

        PrgState prgState4 = new PrgState(exeStack4, SymTbl4, Out4, FileTable4, Heap4, ex4);

        IStmt ex5 = new PrintStmt(new RelationalExp("==", new ValueExp(new IntValue(6)), new ValueExp(new IntValue(7))));
        MyIStack<IStmt> exeStack5 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl5 = new MyDictionary<String, Value>();
        MyIList<Value> Out5 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap5 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable5 = new FileTable();

        PrgState prgState5 = new PrgState(exeStack5, SymTbl5, Out5, FileTable5, Heap5, ex5);

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(5))),
                        new CompStmt(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1)))))), new NopStmt())));

        MyIStack<IStmt> exeStack6 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl6 = new MyDictionary<String, Value>();
        MyIList<Value> Out6 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap6 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable6 = new FileTable();

        PrgState prgState6 = new PrgState(exeStack6, SymTbl6, Out6, FileTable6, Heap6, ex6);

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        MyIStack<IStmt> exeStack7 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl7 = new MyDictionary<String, Value>();
        MyIList<Value> Out7 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap7 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable7 = new FileTable();

        PrgState prgState7 = new PrgState(exeStack7, SymTbl7, Out7, FileTable7, Heap7, ex7);

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))), new PrintStmt(new ArithExp("+", new rHExp(new rHExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));

        MyIStack<IStmt> exeStack8 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl8 = new MyDictionary<String, Value>();
        MyIList<Value> Out8 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap8 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable8 = new FileTable();

        PrgState prgState8 = new PrgState(exeStack8, SymTbl8, Out8, FileTable8, Heap8, ex8);


        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))), new CompStmt(new wHStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp("+", new rHExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

        MyIStack<IStmt> exeStack9 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl9 = new MyDictionary<String, Value>();
        MyIList<Value> Out9 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap9 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable9 = new FileTable();

        PrgState prgState9 = new PrgState(exeStack9, SymTbl9, Out9, FileTable9, Heap9, ex9);


        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));

        MyIStack<IStmt> exeStack10 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl10 = new MyDictionary<String, Value>();
        MyIList<Value> Out10 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap10 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable10 = new FileTable();

        PrgState prgState10 = new PrgState(exeStack10, SymTbl10, Out10, FileTable10, Heap10, ex10);

        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new aHStmt("v", new ValueExp(new IntValue(30)))));

        MyIStack<IStmt> exeStack11 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl11 = new MyDictionary<String, Value>();
        MyIList<Value> Out11 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap11 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable11 = new FileTable();

        PrgState prgState11 = new PrgState(exeStack11, SymTbl11, Out11, FileTable11, Heap11, ex11);


        Scanner sc = new Scanner(System.in);

        System.out.println("Write the name of the logFile:");
        String logFile = sc.nextLine();

        IRepository repo1 = new Repository(prgState1, logFile);
        Controller controller1 = new Controller(repo1);
        controller1.setFlag();

        IRepository repo2 = new Repository(prgState2, logFile);
        Controller controller2 = new Controller(repo2);
        controller2.setFlag();

        IRepository repo3 = new Repository(prgState3, logFile);
        Controller controller3 = new Controller(repo3);
        controller3.setFlag();

        IRepository repo4 = new Repository(prgState4, logFile);
        Controller controller4 = new Controller(repo4);
        controller4.setFlag();

        IRepository repo5 = new Repository(prgState5, logFile);
        Controller controller5 = new Controller(repo5);
        controller5.setFlag();

        IRepository repo6 = new Repository(prgState6, logFile);
        Controller controller6 = new Controller(repo6);
        controller6.setFlag();

        IRepository repo7 = new Repository(prgState7, logFile);
        Controller controller7 = new Controller(repo7);
        controller7.setFlag();

        IRepository repo8 = new Repository(prgState8, logFile);
        Controller controller8 = new Controller(repo8);
        controller8.setFlag();

        IRepository repo9 = new Repository(prgState9, logFile);
        Controller controller9 = new Controller(repo9);
        controller9.setFlag();

        IRepository repo10 = new Repository(prgState10, logFile);
        Controller controller10 = new Controller(repo10);
        controller10.setFlag();

        IRepository repo11 = new Repository(prgState11, logFile);
        Controller controller11 = new Controller(repo11);
        controller11.setFlag();


        while(true) {
            try {
                TextMenu menu = new TextMenu();
                menu.addCommand(new ExitCommand("0", "exit"));
                menu.addCommand(new RunExample("1", ex1.toString(), controller1));
                menu.addCommand(new RunExample("2", ex2.toString(), controller2));
                menu.addCommand(new RunExample("3", ex3.toString(), controller3));
                menu.addCommand(new RunExample("4", ex4.toString(), controller4));
                menu.addCommand(new RunExample("5", ex5.toString(), controller5));
                menu.addCommand(new RunExample("6", ex6.toString(), controller6));
                menu.addCommand(new RunExample("7", ex7.toString(), controller7));
                menu.addCommand(new RunExample("8", ex8.toString(), controller8));
                menu.addCommand(new RunExample("9", ex9.toString(), controller9));
                menu.addCommand(new RunExample("10", ex10.toString(), controller10));
                menu.addCommand(new RunExample("11", ex11.toString(), controller11));
                menu.show();
            }
            catch (MyException exception) {
                System.out.println(exception.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    }
