package ast;

import assembler.IAssembler;
import environment.IEnvironment;
import exception.DivisionException;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import type.IType;
import type.MemoryType;
import value.IValue;
import value.MemoryValue;

public class ASTReference implements ASTNode {

    private ASTNode expression;
    private IType expressionType;

    public ASTReference(ASTNode expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        IValue expressionValue = expression.evaluate(environment);
        return new MemoryValue(expressionValue);
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType referenceType = expression.typecheck(environment);
        return expressionType = new MemoryType(referenceType);
    }

    @Override
    public void assemble(IAssembler assembler) {
        assembler.reference(((MemoryType) expressionType).getType());
        assembler.dup();
        assembler.referenceinvokespecial(((MemoryType) expressionType).getType());
        assembler.dup();
        expression.assemble(assembler);
        assembler.referenceputfield(((MemoryType) expressionType).getType());
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
