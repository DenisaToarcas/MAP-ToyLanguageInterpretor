package view;

import controller.Controller;
import exception.MyException;
import model.adts.*;
import model.expressions.*;
import model.prgState.PrgState;
import model.statements.*;
import model.types.*;
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
        MyIDictionary<String, Type> typeEnv1 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

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
        MyIDictionary<String, Type> typeEnv2 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

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
        MyIDictionary<String, Type> typeEnv3 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("src/test.in"))), new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))));

        MyIStack<IStmt> exeStack4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl4 = new MyDictionary<String, Value>();
        MyIList<Value> Out4 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap4 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable4 = new FileTable();
        MyIDictionary<String, Type> typeEnv4 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        IStmt ex5 = new PrintStmt(new RelationalExp("==", new ValueExp(new IntValue(6)), new ValueExp(new IntValue(7))));
        MyIStack<IStmt> exeStack5 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl5 = new MyDictionary<String, Value>();
        MyIList<Value> Out5 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap5 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable5 = new FileTable();
        MyIDictionary<String, Type> typeEnv5 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(5))),
                        new CompStmt(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1)))))), new NopStmt())));

        MyIStack<IStmt> exeStack6 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl6 = new MyDictionary<String, Value>();
        MyIList<Value> Out6 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap6 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable6 = new FileTable();
        MyIDictionary<String, Type> typeEnv6 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        MyIStack<IStmt> exeStack7 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl7 = new MyDictionary<String, Value>();
        MyIList<Value> Out7 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap7 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable7 = new FileTable();
        MyIDictionary<String, Type> typeEnv7 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))), new PrintStmt(new ArithExp("+", new rHExp(new rHExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));

        MyIStack<IStmt> exeStack8 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl8 = new MyDictionary<String, Value>();
        MyIList<Value> Out8 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap8 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable8 = new FileTable();
        MyIDictionary<String, Type> typeEnv8 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------


        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))), new CompStmt(new wHStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp("+", new rHExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

        MyIStack<IStmt> exeStack9 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl9 = new MyDictionary<String, Value>();
        MyIList<Value> Out9 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap9 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable9 = new FileTable();
        MyIDictionary<String, Type> typeEnv9 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------


        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));

        MyIStack<IStmt> exeStack10 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl10 = new MyDictionary<String, Value>();
        MyIList<Value> Out10 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap10 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable10 = new FileTable();
        MyIDictionary<String, Type> typeEnv10 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new aHStmt("v", new ValueExp(new IntValue(30)))));

        MyIStack<IStmt> exeStack11 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl11 = new MyDictionary<String, Value>();
        MyIList<Value> Out11 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap11 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable11 = new FileTable();
        MyIDictionary<String, Type> typeEnv11 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        IStmt ex12 = new CompStmt(
                new VarDeclStmt("v", new IntType()), new CompStmt(
                        new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(
                                new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(
                                        new aHStmt("a", new ValueExp(new IntValue(22))), new CompStmt(
                                                new ForkStmt(new CompStmt(new wHStmt("a", new ValueExp(new IntValue(30))), new CompStmt(
                                                        new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(
                                                        new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new rHExp(new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a")))))))));

        MyIStack<IStmt> exeStack12 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl12 = new MyDictionary<String, Value>();
        MyIList<Value> Out12 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap12 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable12 = new FileTable();
        MyIDictionary<String, Type> typeEnv12 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        IStmt ex13 = new CompStmt(
                new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(
                        new VarDeclStmt("counter", new IntType()),
                                new WhileStmt(new RelationalExp("<", new VarExp("counter"), new ValueExp(new IntValue(10))),
                                        new CompStmt(new ForkStmt(new ForkStmt(new CompStmt(new aHStmt("a", new VarExp("counter")), new CompStmt(new PrintStmt(new VarExp("counter")), new PrintStmt(new rHExp(new VarExp("a"))))))),
                                        new AssignStmt("counter", new ArithExp("+", new VarExp("counter"), new ValueExp(new IntValue(1))))))));

        MyIStack<IStmt> exeStack13 = new MyStack<IStmt>();
        MyIDictionary<String, Value> SymTbl13 = new MyDictionary<String, Value>();
        MyIList<Value> Out13 = new MyList<Value>();
        MyIHeap<Integer, Value> Heap13 = new Heap();
        MyIFileTable<String, BufferedReader> FileTable13 = new FileTable();
        MyIDictionary<String, Type> typeEnv13 = new MyDictionary<>();

        //--------------------------------------------------------------------------------------------------------------

        Scanner sc = new Scanner(System.in);

        System.out.println("Write the name of the logFile:");
        String logFile = sc.nextLine();


        while(true) {
            try {
                TextMenu menu = new TextMenu();
                menu.addCommand(new ExitCommand("0", "exit"));

                try {
                    ex1.typeCheck(typeEnv1);
                    PrgState prgState1 = new PrgState(exeStack1, SymTbl1, Out1, FileTable1, Heap1, ex1);
                    IRepository repo1 = new Repository(prgState1, logFile);
                    Controller controller1 = new Controller(repo1);
                    controller1.setFlag();
                    menu.addCommand(new RunExample("1", ex1.toString(), controller1));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex2.typeCheck(typeEnv2);
                    PrgState prgState2 = new PrgState(exeStack2, SymTbl2, Out2, FileTable2, Heap2, ex2);
                    IRepository repo2 = new Repository(prgState2, logFile);
                    Controller controller2 = new Controller(repo2);
                    controller2.setFlag();
                    menu.addCommand(new RunExample("2", ex2.toString(), controller2));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex3.typeCheck(typeEnv3);
                    PrgState prgState3 = new PrgState(exeStack3, SymTbl3, Out3, FileTable3, Heap3, ex3);
                    IRepository repo3 = new Repository(prgState3, logFile);
                    Controller controller3 = new Controller(repo3);
                    controller3.setFlag();
                    menu.addCommand(new RunExample("3", ex3.toString(), controller3));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex4.typeCheck(typeEnv4);
                    PrgState prgState4 = new PrgState(exeStack4, SymTbl4, Out4, FileTable4, Heap4, ex4);
                    IRepository repo4 = new Repository(prgState4, logFile);
                    Controller controller4 = new Controller(repo4);
                    controller4.setFlag();
                    menu.addCommand(new RunExample("4", ex4.toString(), controller4));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex5.typeCheck(typeEnv5);
                    PrgState prgState5 = new PrgState(exeStack5, SymTbl5, Out5, FileTable5, Heap5, ex5);
                    IRepository repo5 = new Repository(prgState5, logFile);
                    Controller controller5 = new Controller(repo5);
                    controller5.setFlag();
                    menu.addCommand(new RunExample("5", ex5.toString(), controller5));
                    }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex6.typeCheck(typeEnv6);
                    PrgState prgState6 = new PrgState(exeStack6, SymTbl6, Out6, FileTable6, Heap6, ex6);
                    IRepository repo6 = new Repository(prgState6, logFile);
                    Controller controller6 = new Controller(repo6);
                    controller6.setFlag();
                    menu.addCommand(new RunExample("6", ex6.toString(), controller6));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex7.typeCheck(typeEnv7);
                    PrgState prgState7 = new PrgState(exeStack7, SymTbl7, Out7, FileTable7, Heap7, ex7);
                    IRepository repo7 = new Repository(prgState7, logFile);
                    Controller controller7 = new Controller(repo7);
                    controller7.setFlag();
                    menu.addCommand(new RunExample("7", ex7.toString(), controller7));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex8.typeCheck(typeEnv8);
                    PrgState prgState8 = new PrgState(exeStack8, SymTbl8, Out8, FileTable8, Heap8, ex8);
                    IRepository repo8 = new Repository(prgState8, logFile);
                    Controller controller8 = new Controller(repo8);
                    controller8.setFlag();
                    menu.addCommand(new RunExample("8", ex8.toString(), controller8));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex9.typeCheck(typeEnv9);
                    PrgState prgState9 = new PrgState(exeStack9, SymTbl9, Out9, FileTable9, Heap9, ex9);
                    IRepository repo9 = new Repository(prgState9, logFile);
                    Controller controller9 = new Controller(repo9);
                    controller9.setFlag();
                    menu.addCommand(new RunExample("9", ex9.toString(), controller9));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try {
                    ex10.typeCheck(typeEnv10);
                    PrgState prgState10 = new PrgState(exeStack10, SymTbl10, Out10, FileTable10, Heap10, ex10);
                    IRepository repo10 = new Repository(prgState10, logFile);
                    Controller controller10 = new Controller(repo10);
                    controller10.setFlag();
                    menu.addCommand(new RunExample("10", ex10.toString(), controller10));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try {
                    ex11.typeCheck(typeEnv11);
                    PrgState prgState11 = new PrgState(exeStack11, SymTbl11, Out11, FileTable11, Heap11, ex11);
                    IRepository repo11 = new Repository(prgState11, logFile);
                    Controller controller11 = new Controller(repo11);
                    controller11.setFlag();
                    menu.addCommand(new RunExample("11", ex11.toString(), controller11));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex12.typeCheck(typeEnv12);
                    PrgState prgState12 = new PrgState(exeStack12, SymTbl12, Out12, FileTable12, Heap12, ex12);
                    IRepository repo12 = new Repository(prgState12, logFile);
                    Controller controller12 = new Controller(repo12);
                    controller12.setFlag();
                    menu.addCommand(new RunExample("12", ex12.toString(), controller12));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

                try{
                    ex13.typeCheck(typeEnv13);
                    PrgState prgState13 = new PrgState(exeStack13, SymTbl13, Out13, FileTable13, Heap13, ex13);
                    IRepository repo13 = new Repository(prgState13, logFile);
                    Controller controller13 = new Controller(repo13);
                    controller13.setFlag();
                    menu.addCommand(new RunExample("13", ex13.toString(), controller13));
                }
                catch (MyException exception) {
                    throw new MyException(exception.toString());
                }

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
