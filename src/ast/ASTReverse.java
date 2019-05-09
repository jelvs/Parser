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

public class ASTReverse implements ASTNode {

    private ASTNode expression;
    private IType expressionType;

    public ASTReverse(ASTNode expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        IntegerValue integerValue = (IntegerValue) expression.evaluate(environment);
        return new IntegerValue(-integerValue.getValue());
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IType reverseType = expression.typecheck(environment);

        if (reverseType.equals(IntegerType.getType())) {
            return expressionType = IntegerType.getType();
        }

        throw new TypingException("Wrong types in reverse operation!");
    }

    @Override
    public void assemble(IAssembler assembler) {
        expression.assemble(assembler);
        assembler.ineg();
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
