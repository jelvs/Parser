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

public class ASTAssign implements ASTNode {

    private ASTNode leftExpression;
    private ASTNode rightExpression;
    private IType expressionType;

    public ASTAssign(ASTNode leftExpression, ASTNode rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        IValue leftValue = leftExpression.evaluate(environment);
        IValue rightValue = rightExpression.evaluate(environment);

        MemoryValue memoryValue = (MemoryValue) leftValue;
        memoryValue.setValue(rightValue);
        return rightValue;
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType leftType = leftExpression.typecheck(environment);

        if (leftType instanceof MemoryType) {
            MemoryType memoryType = (MemoryType) leftType;
            IType rightType = rightExpression.typecheck(environment);

            if (memoryType.getType().equals(rightType)) {
                return expressionType = rightType;
            }
        }

        throw new TypingException("Wrong types in assign operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        leftExpression.assemble(assembler);
        assembler.dup();
        assembler.referencecheckcast(expressionType);
        rightExpression.assemble(assembler);
        assembler.referenceputfield(expressionType);
        assembler.referencecheckcast(expressionType);
        assembler.referencegetfield(expressionType);
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}

