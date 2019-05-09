package ast;

import assembler.IAssembler;
import environment.IEnvironment;
import exception.DivisionException;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import type.IType;
import type.IntegerType;
import value.IValue;
import value.IntegerValue;

public class ASTInteger implements ASTNode {

    private int value;

    public ASTInteger(int value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        return new IntegerValue(value);
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        return IntegerType.getType();
    }

    @Override
    public void assemble(IAssembler assembler) {
        assembler.sipush(value);
    }

    @Override
    public IType getType() {
        return IntegerType.getType();
    }
}
