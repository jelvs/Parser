package type;

public class BooleanType implements IType {

    private static final IType type = new BooleanType();

    private BooleanType() {
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
