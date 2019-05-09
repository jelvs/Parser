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

public class ASTWhile implements ASTNode {

    private ASTNode conditionExpression;
    private ASTNode bodyExpression;
    private IType expressionType;

    public ASTWhile(ASTNode conditionExpression, ASTNode bodyExpression) {
        this.conditionExpression = conditionExpression;
        this.bodyExpression = bodyExpression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException, DivisionException {
        BooleanValue conditionValue = (BooleanValue) conditionExpression.evaluate(environment);

        if (conditionValue.getValue()) {
            bodyExpression.evaluate(environment);
            new ASTWhile(conditionExpression, bodyExpression).evaluate(environment);
        }

        return new BooleanValue(false);
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType conditionType = conditionExpression.typecheck(environment);

        if (conditionType.equals(BooleanType.getType())) {
            bodyExpression.typecheck(environment);
            return expressionType = BooleanType.getType();
        }

        throw new TypingException("Wrong types in while operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        String loopLabel = assembler.getLabel();
        String exitLabel = assembler.getLabel();

        assembler.anchor(loopLabel);
        conditionExpression.assemble(assembler);
        assembler.ifeq(exitLabel);
        bodyExpression.assemble(assembler);
        assembler.pop();
        assembler.jump(loopLabel);
        assembler.anchor(exitLabel);
        assembler.iconst(false);
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
