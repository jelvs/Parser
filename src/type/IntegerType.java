package type;

public class IntegerType implements IType {

    private static final IType type = new IntegerType();

    private IntegerType() {
    }

    public static IType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return true;
    }
}
