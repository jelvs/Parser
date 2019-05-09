package ast;

import assembler.IAssembler;
import environment.IEnvironment;
import exception.DivisionException;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import type.BooleanType;
import type.IType;
import value.BooleanValue;
import value.IValue;

public class ASTBoolean implements ASTNode {

    private boolean value;

    public ASTBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        return new BooleanValue(value);
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        return BooleanType.getType();
    }

    @Override
    public void assemble(IAssembler assembler) {
        assembler.iconst(value);
    }

    @Override
    public IType getType() {
        return BooleanType.getType();
    }
}
