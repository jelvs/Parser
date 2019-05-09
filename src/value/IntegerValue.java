package value;

public class IntegerValue implements IValue {

    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
