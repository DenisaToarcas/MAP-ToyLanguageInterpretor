package org.example.gui_toylanguageinterpretor;

import controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import model.adts.*;
import model.expressions.*;
import model.prgState.PrgState;
import model.statements.*;
import model.types.*;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrgStatesExecutionController implements Initializable {

    private int PrgStateIdentifierSelected = -1;
    private ObservableList<String> prgStateIdentifiers;
    private ArrayList<PrgState> prgStates;
    private Controller controller;

    //---------HEAP----------------------------
    @FXML
    private Label HeapLabel;

    @FXML
    private TableView<Pair<String, String>> HeapTableView;

    @FXML
    private TableColumn<Pair<String, String>, String> AddressTableColumn;

    @FXML
    private TableColumn<Pair<String, String>, String> ValueTableColumn;

    //---------OUT----------------------------
    @FXML
    private Label OutLabel;

    @FXML
    private ListView<String> OutListView;

    //---------SYM TABLE----------------------------
    @FXML
    private Label SymTableLabel;

    @FXML
    private TableView<Pair<String, String>> SymTableTableView;

    @FXML
    private TableColumn<Pair<String, String>, String> VariableNameTableColumn;

    @FXML
    private TableColumn<Pair<String, String>, String> ValueSymTblTableColumn;

    //---------EXE STACK----------------------------
    @FXML
    private Label ExeStackLabel;

    @FXML
    private ListView<String> ExeStackListView;

    //---------FILE TABLE----------------------------
    @FXML
    private Label FileTableLabel;

    @FXML
    private ListView<String> FileTableListView;

    //---------PRG STATES----------------------------

    @FXML
    private Label PrgStateIdentifiersLabel;

    @FXML
    private ListView<String> PrgStateIdentifiersListView;

    @FXML
    private Label NoOfPrgStatesLabel;

    @FXML
    private TextField NoOfPrgStatesTextField;

    @FXML
    private Button RunOneStepButton;

    //-------------------------------------------------------------------------
    IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

    MyIStack<IStmt> exeStack1 = new MyStack<IStmt>();
    MyIDictionary<String, Value> SymTbl1 = new MyDictionary<String, Value>();
    MyIList<Value> Out1 = new MyList<Value>();
    MyIHeap<Integer, Value> Heap1 = new Heap();
    MyIFileTable<String, BufferedReader> FileTable1 = new FileTable();
    MyIDictionary<String, Type> typeEnv1 = new MyDictionary<>();
    PrgState prgState1 = new PrgState(exeStack1, SymTbl1, Out1, FileTable1, Heap1, ex1);

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
    PrgState prgState2 = new PrgState(exeStack2, SymTbl2, Out2, FileTable2, Heap2, ex2);

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
    PrgState prgState3 = new PrgState(exeStack3, SymTbl3, Out3, FileTable3, Heap3, ex3);

    //--------------------------------------------------------------------------------------------------------------

    IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))));

    MyIStack<IStmt> exeStack4 = new MyStack<IStmt>();
    MyIDictionary<String, Value> SymTbl4 = new MyDictionary<String, Value>();
    MyIList<Value> Out4 = new MyList<Value>();
    MyIHeap<Integer, Value> Heap4 = new Heap();
    MyIFileTable<String, BufferedReader> FileTable4 = new FileTable();
    MyIDictionary<String, Type> typeEnv4 = new MyDictionary<>();
    PrgState prgState4 = new PrgState(exeStack4, SymTbl4, Out4, FileTable4, Heap4, ex4);

    //--------------------------------------------------------------------------------------------------------------

    IStmt ex5 = new PrintStmt(new RelationalExp("==", new ValueExp(new IntValue(6)), new ValueExp(new IntValue(7))));
    MyIStack<IStmt> exeStack5 = new MyStack<IStmt>();
    MyIDictionary<String, Value> SymTbl5 = new MyDictionary<String, Value>();
    MyIList<Value> Out5 = new MyList<Value>();
    MyIHeap<Integer, Value> Heap5 = new Heap();
    MyIFileTable<String, BufferedReader> FileTable5 = new FileTable();
    MyIDictionary<String, Type> typeEnv5 = new MyDictionary<>();
    PrgState prgState5 = new PrgState(exeStack5, SymTbl5, Out5, FileTable5, Heap5, ex5);

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
    PrgState prgState6 = new PrgState(exeStack6, SymTbl6, Out6, FileTable6, Heap6, ex6);

    //--------------------------------------------------------------------------------------------------------------

    IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

    MyIStack<IStmt> exeStack7 = new MyStack<IStmt>();
    MyIDictionary<String, Value> SymTbl7 = new MyDictionary<String, Value>();
    MyIList<Value> Out7 = new MyList<Value>();
    MyIHeap<Integer, Value> Heap7 = new Heap();
    MyIFileTable<String, BufferedReader> FileTable7 = new FileTable();
    MyIDictionary<String, Type> typeEnv7 = new MyDictionary<>();
    PrgState prgState7 = new PrgState(exeStack7, SymTbl7, Out7, FileTable7, Heap7, ex7);

    //--------------------------------------------------------------------------------------------------------------

    IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))), new PrintStmt(new ArithExp("+", new rHExp(new rHExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));

    MyIStack<IStmt> exeStack8 = new MyStack<IStmt>();
    MyIDictionary<String, Value> SymTbl8 = new MyDictionary<String, Value>();
    MyIList<Value> Out8 = new MyList<Value>();
    MyIHeap<Integer, Value> Heap8 = new Heap();
    MyIFileTable<String, BufferedReader> FileTable8 = new FileTable();
    MyIDictionary<String, Type> typeEnv8 = new MyDictionary<>();
    PrgState prgState8 = new PrgState(exeStack8, SymTbl8, Out8, FileTable8, Heap8, ex8);

    //--------------------------------------------------------------------------------------------------------------


    IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))), new CompStmt(new wHStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp("+", new rHExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

    MyIStack<IStmt> exeStack9 = new MyStack<IStmt>();
    MyIDictionary<String, Value> SymTbl9 = new MyDictionary<String, Value>();
    MyIList<Value> Out9 = new MyList<Value>();
    MyIHeap<Integer, Value> Heap9 = new Heap();
    MyIFileTable<String, BufferedReader> FileTable9 = new FileTable();
    MyIDictionary<String, Type> typeEnv9 = new MyDictionary<>();
    PrgState prgState9 = new PrgState(exeStack9, SymTbl9, Out9, FileTable9, Heap9, ex9);

    //--------------------------------------------------------------------------------------------------------------


    IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));

    MyIStack<IStmt> exeStack10 = new MyStack<IStmt>();
    MyIDictionary<String, Value> SymTbl10 = new MyDictionary<String, Value>();
    MyIList<Value> Out10 = new MyList<Value>();
    MyIHeap<Integer, Value> Heap10 = new Heap();
    MyIFileTable<String, BufferedReader> FileTable10 = new FileTable();
    MyIDictionary<String, Type> typeEnv10 = new MyDictionary<>();
    PrgState prgState10 = new PrgState(exeStack10, SymTbl10, Out10, FileTable10, Heap10, ex10);

    //--------------------------------------------------------------------------------------------------------------

    IStmt ex11 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new aHStmt("v", new ValueExp(new IntValue(30)))));

    MyIStack<IStmt> exeStack11 = new MyStack<IStmt>();
    MyIDictionary<String, Value> SymTbl11 = new MyDictionary<String, Value>();
    MyIList<Value> Out11 = new MyList<Value>();
    MyIHeap<Integer, Value> Heap11 = new Heap();
    MyIFileTable<String, BufferedReader> FileTable11 = new FileTable();
    MyIDictionary<String, Type> typeEnv11 = new MyDictionary<>();
    PrgState prgState11 = new PrgState(exeStack11, SymTbl11, Out11, FileTable11, Heap11, ex11);

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
    PrgState prgState12 = new PrgState(exeStack12, SymTbl12, Out12, FileTable12, Heap12, ex12);

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
    PrgState prgState13 = new PrgState(exeStack13, SymTbl13, Out13, FileTable13, Heap13, ex13);

    //--------------------------------------------------------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        prgStateIdentifiers = FXCollections.observableArrayList();
        prgStates = new ArrayList<>();

        addPrgStatesANDIdentifiers();
        this.NoOfPrgStatesTextField.setText(String.valueOf(this.prgStates.size()));
        populatePrgStateIdentifiersListView();
    }

    public void addPrgStatesANDIdentifiers()
    {
        prgStates.add(prgState1);
        prgStateIdentifiers.add(String.valueOf(prgState1.getPrgStateID()));

        prgStates.add(prgState2);
        prgStateIdentifiers.add(String.valueOf(prgState2.getPrgStateID()));

        prgStates.add(prgState3);
        prgStateIdentifiers.add(String.valueOf(prgState3.getPrgStateID()));

        prgStates.add(prgState4);
        prgStateIdentifiers.add(String.valueOf(prgState4.getPrgStateID()));

        prgStates.add(prgState5);
        prgStateIdentifiers.add(String.valueOf(prgState5.getPrgStateID()));

        prgStates.add(prgState6);
        prgStateIdentifiers.add(String.valueOf(prgState6.getPrgStateID()));

        prgStates.add(prgState7);
        prgStateIdentifiers.add(String.valueOf(prgState7.getPrgStateID()));

        prgStates.add(prgState8);
        prgStateIdentifiers.add(String.valueOf(prgState8.getPrgStateID()));

        prgStates.add(prgState9);
        prgStateIdentifiers.add(String.valueOf(prgState9.getPrgStateID()));

        prgStates.add(prgState10);
        prgStateIdentifiers.add(String.valueOf(prgState10.getPrgStateID()));

        prgStates.add(prgState11);
        prgStateIdentifiers.add(String.valueOf(prgState11.getPrgStateID()));

        prgStates.add(prgState12);
        prgStateIdentifiers.add(String.valueOf(prgState12.getPrgStateID()));

        prgStates.add(prgState13);
        prgStateIdentifiers.add(String.valueOf(prgState13.getPrgStateID()));
    }

    public void populatePrgStateIdentifiersListView()
    {
        this.PrgStateIdentifiersListView.setItems(prgStateIdentifiers);
    }

    public void checkIfPrgStateIdentifierSelected()
    {
        this.PrgStateIdentifierSelected = PrgStateIdentifiersListView.getSelectionModel().getSelectedIndex();
        this.ExeStackListView.setItems(
                FXCollections.observableList(this.prgStates.get(PrgStateIdentifierSelected).getStk().getToString())
        );
    }

    public void setController(Controller contr)
    {
        this.controller = contr;
    }
}

