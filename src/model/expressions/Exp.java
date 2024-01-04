package model.expressions;

import exception.MyException;
import model.adts.MyIHeap;
import model.types.Type;
import model.values.*;

import model.adts.MyIDictionary;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> heap) throws MyException;

    Type typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException;

    Exp deepCopy();
}
