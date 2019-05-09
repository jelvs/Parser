package ast;

import assembler.IAssembler;
import assembler.StackFrame;
import assembler.StackVariable;
import environment.IEnvironment;
import exception.DivisionException;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import type.IType;
import value.IValue;

public class ASTIdentifier implements ASTNode {

    private String identifier;
    private IType expressionType;

    public ASTIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        return environment.find(identifier);
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        return expressionType = environment.find(identifier);
    }

    @Override
    public void assemble(IAssembler assembler) {
        StackFrame current = assembler.aload();

        while (current != null) {
            for (StackVariable field : current.getFields()) {
                if (field.getIdentifier().equals(identifier)) {
                    assembler.framegetfield(current.getIdentifier(), field.getName(), field.getType());
                    return;
                }
            }

            StackFrame ancestor = current.getAncestor();

            assembler.framegetstaticlink(current.getIdentifier(), ancestor != null ? ancestor.getIdentifier() : null);
            current = ancestor;
        }
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
