package ast;

import assembler.IAssembler;
import environment.IEnvironment;
import exception.DivisionException;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import type.BooleanType;
import type.IType;
import type.IntegerType;
import value.BooleanValue;
import value.IValue;
import value.IntegerValue;

public class ASTNotEqual implements ASTNode {

    private ASTNode leftExpression;
    private ASTNode rightExpression;
    private IType expressionType;

    public ASTNotEqual(ASTNode leftExpression, ASTNode rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        IValue leftValue = leftExpression.evaluate(environment);
        IValue rightValue = rightExpression.evaluate(environment);

        if (leftValue instanceof IntegerValue && rightValue instanceof IntegerValue) {
            IntegerValue leftInteger = (IntegerValue) leftValue;
            IntegerValue rightInteger = (IntegerValue) rightValue;
            return new BooleanValue(leftInteger.getValue() != rightInteger.getValue());
        } else if (leftValue instanceof BooleanValue && rightValue instanceof BooleanValue) {
            BooleanValue leftBoolean = (BooleanValue) leftValue;
            BooleanValue rightBoolean = (BooleanValue) rightValue;
            return new BooleanValue(leftBoolean.getValue() != rightBoolean.getValue());
        }

        return null; // Just to keep typechecker happy!
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType leftType = leftExpression.typecheck(environment);
        IType rightType = rightExpression.typecheck(environment);

        if ((leftType.equals(IntegerType.getType()) && rightType.equals(IntegerType.getType()))
                || (leftType.equals(BooleanType.getType()) && rightType.equals(BooleanType.getType()))) {
            return expressionType = BooleanType.getType();
        }

        throw new TypingException("Wrong types in not equal operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        String falseLabel = assembler.getLabel();
        String exitLabel = assembler.getLabel();

        leftExpression.assemble(assembler);
        rightExpression.assemble(assembler);
        assembler.if_icmpeq(falseLabel);
        assembler.iconst(true);
        assembler.jump(exitLabel);
        assembler.anchor(falseLabel);
        assembler.iconst(false);
        assembler.anchor(exitLabel);
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
