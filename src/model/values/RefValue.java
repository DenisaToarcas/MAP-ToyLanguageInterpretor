package model.values;
import model.types.*;

import java.util.Objects;

public class RefValue implements Value{
    private int address;
    private final Type locationType;

    public RefValue(int address, Type locationType)
    {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public String toString()
    {
        return "(" + this.address + ", " + this.locationType.toString() + ")";
    }

    @Override
    public boolean equals(Object another) {
        if (this == another)
            return true;

        if (another instanceof RefValue) {
            if (Objects.equals(this.address, ((RefValue) another).getAddress()))
                if (Objects.equals(this.locationType, ((RefValue) another).getInnerType()))
                    return true;
        }
        return false;
    }

    public int getAddress()
    {
        return this.address;
    }

    public RefValue setAddress(int newAddress)
    {
        return new RefValue(newAddress, this.locationType);
    }

    @Override
    public Type getType()
    {
        return new RefType(this.locationType);
    }

    public Type getInnerType()
    {
        return this.locationType;
    }

    @Override
    public Value deepCopy()
    {
        return new RefValue(this.address, this.locationType.deepCopy());
    }
}
