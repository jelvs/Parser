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

public class ASTDereference implements ASTNode {

    private ASTNode expression;
    private IType expressionType;

    public ASTDereference(ASTNode expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        MemoryValue memoryValue = (MemoryValue) expression.evaluate(environment);
        return memoryValue.getValue();
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType memoryType = expression.typecheck(environment);

        if (memoryType instanceof MemoryType) {
            return expressionType = ((MemoryType) memoryType).getType();
        }

        throw new TypingException("Wrong types in dereference operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        expression.assemble(assembler);
        assembler.referencecheckcast(expressionType);
        assembler.referencegetfield(expressionType);
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
