package assembler;

import type.IType;

public class StackVariable {

    private final String identifier;
    private final String name;
    private final IType type;

    public StackVariable(String identifier, String name, IType type) {
        this.identifier = identifier;
        this.name = name;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public IType getType() {
        return type;
    }
}
