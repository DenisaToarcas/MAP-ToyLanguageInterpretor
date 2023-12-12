package model.types;
import model.values.*;

public interface Type {
    Value defaultValue();

    Type deepCopy();
}
