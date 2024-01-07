package org.example.gui_toylanguageinterpretor;

import controller.Controller;
import exception.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrgStatesSelectionController implements Initializable {
    private ObservableList<String> prgStatesObservableList;
    private ArrayList<IStmt> prgStates;
    private int PrgStateSelected = -1;
    private final String logFile = "gui.txt";
    private PrgStatesExecutionController executionController;

    @FXML
    private ListView<String> PrgStateListView;

    @FXML
    private Label PrgStateLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prgStatesObservableList = FXCollections.observableArrayList();
        prgStates = new ArrayList<IStmt>();

        populatePrgStateListView();
    }

    public void checkIfPrgStateSelected()
    {
        this.PrgStateSelected = PrgStateListView.getSelectionModel().getSelectedIndex();

        try{
            IStmt stmtToExecute = this.prgStates.get(PrgStateSelected);

            MyIStack<IStmt> exeStack = new MyStack<IStmt>();
            MyIDictionary<String, Value> SymTbl = new MyDictionary<String, Value>();
            MyIList<Value> Out = new MyList<Value>();
            MyIHeap<Integer, Value> Heap = new Heap();
            MyIFileTable<String, BufferedReader> FileTable = new FileTable();
            MyIDictionary<String, Type> typeEnv = new MyDictionary<>();

            stmtToExecute.typeCheck(typeEnv);
            PrgState prgState = new PrgState(exeStack, SymTbl, Out, FileTable, Heap, stmtToExecute);
            IRepository repo = new Repository(prgState, logFile);
            Controller controller = new Controller(repo);
            controller.setFlag();

            executionController.setController(controller);

        }catch (MyException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.toString(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        }
    }

    public void populatePrgStateListView()
    {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        prgStatesObservableList.add("1. "+ex1.toString());
        prgStates.add(ex1);

        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp("+", new ValueExp(new IntValue(2)), new
                                ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        prgStatesObservableList.add("2. "+ex2.toString());
        prgStates.add(ex2);

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        prgStatesObservableList.add("3. "+ex3.toString());
        prgStates.add(ex3);

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))));
        prgStatesObservableList.add("4. "+ex4.toString());
        prgStates.add(ex4);

        IStmt ex5 = new PrintStmt(new RelationalExp("==", new ValueExp(new IntValue(6)), new ValueExp(new IntValue(7))));
        prgStatesObservableList.add("5. "+ex5.toString());
        prgStates.add(ex5);

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(5))),
                        new CompStmt(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ValueExp(new IntValue(1)))))), new NopStmt())));
        prgStatesObservableList.add("6. "+ex6.toString());
        prgStates.add(ex6);

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        prgStatesObservableList.add("7. "+ex7.toString());
        prgStates.add(ex7);

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))), new PrintStmt(new ArithExp("+", new rHExp(new rHExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        prgStatesObservableList.add("8. "+ex8.toString());
        prgStates.add(ex8);

        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))), new CompStmt(new wHStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp("+", new rHExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        prgStatesObservableList.add("9. "+ex9.toString());
        prgStates.add(ex9);

        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new aHStmt("a", new VarExp("v")), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));
        prgStatesObservableList.add("10. "+ex10.toString());
        prgStates.add(ex10);

        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new aHStmt("v", new ValueExp(new IntValue(20))), new aHStmt("v", new ValueExp(new IntValue(30)))));
        prgStatesObservableList.add("11. "+ex11.toString());
        prgStates.add(ex11);

        IStmt ex12 = new CompStmt(
                new VarDeclStmt("v", new IntType()), new CompStmt(
                new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(
                new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(
                new aHStmt("a", new ValueExp(new IntValue(22))), new CompStmt(
                new ForkStmt(new CompStmt(new wHStmt("a", new ValueExp(new IntValue(30))), new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(
                        new PrintStmt(new VarExp("v")),
                        new PrintStmt(new rHExp(new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rHExp(new VarExp("a")))))))));
        prgStatesObservableList.add("12. "+ex12.toString());
        prgStates.add(ex12);

        IStmt ex13 = new CompStmt(
                new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(
                new VarDeclStmt("counter", new IntType()),
                new WhileStmt(new RelationalExp("<", new VarExp("counter"), new ValueExp(new IntValue(10))),
                        new CompStmt(new ForkStmt(new ForkStmt(new CompStmt(new aHStmt("a", new VarExp("counter")), new CompStmt(new PrintStmt(new VarExp("counter")), new PrintStmt(new rHExp(new VarExp("a"))))))),
                                new AssignStmt("counter", new ArithExp("+", new VarExp("counter"), new ValueExp(new IntValue(1))))))));
        prgStatesObservableList.add("13. "+ex13.toString());
        prgStates.add(ex13);

        this.PrgStateListView.setItems(prgStatesObservableList);
    }

    public void setExecutionController(PrgStatesExecutionController executionController)
    {
        this.executionController = executionController;
    }

}

