package ast;

import assembler.IAssembler;
import environment.IEnvironment;
import exception.DivisionException;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import type.IType;
import value.IValue;

import java.util.List;

public class ASTSequence implements ASTNode {

    private List<ASTNode> expressions;
    private IType expressionType;

    public ASTSequence(List<ASTNode> expressions) {
        this.expressions = expressions;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        IValue expressionValue = null;

        for (ASTNode expression : expressions) {
            expressionValue = expression.evaluate(environment);
        }

        return expressionValue;
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {

        for (ASTNode expression : expressions) {
            expressionType = expression.typecheck(environment);
        }

        return expressionType;
    }

    @Override
    public void assemble(IAssembler assembler) {
        for (int i = 0; i < expressions.size() - 1; i++) {
            expressions.get(i).assemble(assembler);
            assembler.pop();
        }

        expressions.get(expressions.size() - 1).assemble(assembler);
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
