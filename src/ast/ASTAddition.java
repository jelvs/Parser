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

public class ASTAddition implements ASTNode {

    private ASTNode leftExpression;
    private ASTNode rightExpression;
    private IType expressionType;

    public ASTAddition(ASTNode leftExpression, ASTNode rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        IntegerValue leftValue = (IntegerValue) leftExpression.evaluate(environment);
        IntegerValue rightValue = (IntegerValue) rightExpression.evaluate(environment);
        return new IntegerValue(leftValue.getValue() + rightValue.getValue());
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType leftType = leftExpression.typecheck(environment);
        IType rightType = rightExpression.typecheck(environment);

        if (leftType.equals(IntegerType.getType()) && rightType.equals(IntegerType.getType())) {
            return expressionType = IntegerType.getType();
        }

        throw new TypingException("Wrong types in addition operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        leftExpression.assemble(assembler);
        rightExpression.assemble(assembler);
        assembler.iadd();
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
