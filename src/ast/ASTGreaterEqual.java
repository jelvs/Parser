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

public class ASTGreaterEqual implements ASTNode {

    private ASTNode leftExpression;
    private ASTNode rightExpression;
    private IType expressionType;

    public ASTGreaterEqual(ASTNode leftExpression, ASTNode rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        IntegerValue leftValue = (IntegerValue) leftExpression.evaluate(environment);
        IntegerValue rightValue = (IntegerValue) rightExpression.evaluate(environment);
        return new BooleanValue(leftValue.getValue() >= rightValue.getValue());
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType leftType = leftExpression.typecheck(environment);
        IType rightType = rightExpression.typecheck(environment);

        if (leftType.equals(IntegerType.getType()) && rightType.equals(IntegerType.getType())) {
            return expressionType = BooleanType.getType();
        }

        throw new TypingException("Wrong types in greater equal operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        String falseLabel = assembler.getLabel();
        String exitLabel = assembler.getLabel();

        leftExpression.assemble(assembler);
        rightExpression.assemble(assembler);
        assembler.if_icmplt(falseLabel);
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
