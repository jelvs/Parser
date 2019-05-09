package assembler;

import type.IType;

import java.util.ArrayList;
import java.util.List;

public class StackFrame {

    private static final String LOC_PREFIX = "loc_";

    private final String identifier;
    private StackFrame ancestor;
    private List<StackVariable> fields;

    public StackFrame(String identifier) {
        this.identifier = identifier;
        this.ancestor = null;
        this.fields = new ArrayList<StackVariable>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public StackFrame getAncestor() {
        return ancestor;
    }

    public void setAncestor(StackFrame ancestor) {
        this.ancestor = ancestor;
    }

    public List<StackVariable> getFields() {
        return fields;
    }

    public String addField(String identifier, IType type) {
        String name = LOC_PREFIX + fields.size();
        fields.add(new StackVariable(identifier, name, type));
        return name;
    }
}
