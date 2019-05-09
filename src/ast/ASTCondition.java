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

public class ASTCondition implements ASTNode {

    private ASTNode conditionExpression;
    private ASTNode mainExpression;
    private ASTNode secondaryExpression;
    private IType expressionType;

    public ASTCondition(ASTNode conditionExpression, ASTNode mainExpression, ASTNode secondaryExpression) {
        this.conditionExpression = conditionExpression;
        this.mainExpression = mainExpression;
        this.secondaryExpression = secondaryExpression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        BooleanValue conditionValue = (BooleanValue) conditionExpression.evaluate(environment);

        if (conditionValue.getValue()) {
            return mainExpression.evaluate(environment);
        }

        return secondaryExpression.evaluate(environment);
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType conditionType = conditionExpression.typecheck(environment);

        if (conditionType.equals(BooleanType.getType())) {
            IType mainType = mainExpression.typecheck(environment);
            IType secondaryType = secondaryExpression.typecheck(environment);

            if (mainType.equals(secondaryType)) {
                return expressionType = mainType;
            }
        }

        throw new TypingException("Wrong types in condition operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        String falseLabel = assembler.getLabel();
        String exitLabel = assembler.getLabel();

        conditionExpression.assemble(assembler);
        assembler.ifeq(falseLabel);
        mainExpression.assemble(assembler);
        assembler.jump(exitLabel);
        assembler.anchor(falseLabel);
        secondaryExpression.assemble(assembler);
        assembler.anchor(exitLabel);
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
