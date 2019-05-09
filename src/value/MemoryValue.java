package value;

public class MemoryValue implements IValue {

    private IValue value;

    public MemoryValue(IValue value) {
        this.value = value;
    }

    public IValue getValue() {
        return value;
    }

    public void setValue(IValue value) {
        this.value = value;
    }
}
