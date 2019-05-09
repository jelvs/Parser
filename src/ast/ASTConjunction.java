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

public class ASTConjunction implements ASTNode {

    private ASTNode leftExpression;
    private ASTNode rightExpression;
    private IType expressionType;

    public ASTConjunction(ASTNode leftExpression, ASTNode rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        BooleanValue leftBoolean = (BooleanValue) leftExpression.evaluate(environment);
        BooleanValue rightBoolean = (BooleanValue) rightExpression.evaluate(environment);
        return new BooleanValue(leftBoolean.getValue() && rightBoolean.getValue());
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType leftType = leftExpression.typecheck(environment);
        IType rightType = rightExpression.typecheck(environment);

        if (leftType.equals(BooleanType.getType()) && rightType.equals(BooleanType.getType())) {
            return expressionType = BooleanType.getType();
        }

        throw new TypingException("Wrong types in conjunction operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        leftExpression.assemble(assembler);
        rightExpression.assemble(assembler);
        assembler.iand();
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
