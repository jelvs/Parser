package type;

public class MemoryType implements IType {

    private IType type;

    public MemoryType(IType type) {
        this.type = type;
    }

    public IType getType() {
        return type;
    }

    public void setType(IType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MemoryType memoryType = (MemoryType) o;
        return type.equals(memoryType.getType());
    }
}
