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

public class ASTNegation implements ASTNode {

    private ASTNode expression;
    private IType expressionType;

    public ASTNegation(ASTNode expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        BooleanValue booleanValue = (BooleanValue) expression.evaluate(environment);
        return new BooleanValue(!booleanValue.getValue());
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType negationType = expression.typecheck(environment);

        if (negationType.equals(BooleanType.getType())) {
            return expressionType = BooleanType.getType();
        }

        throw new TypingException("Wrong types in negation operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        String falseLabel = assembler.getLabel();
        String exitLabel = assembler.getLabel();

        expression.assemble(assembler);
        assembler.ifne(falseLabel);
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
