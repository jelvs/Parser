package ast;

import assembler.IAssembler;
import assembler.StackFrame;
import environment.IEnvironment;
import exception.DivisionException;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import type.IType;
import util.Triplet;
import value.IValue;

import java.util.List;

public class ASTDeclaration implements ASTNode {

    private List<Triplet<String, IType, ASTNode>> definitions;
    private ASTNode bodyExpression;
    private IType expressionType;

    public ASTDeclaration(List<Triplet<String, IType, ASTNode>> definitions, ASTNode bodyExpression) {
        this.definitions = definitions;
        this.bodyExpression = bodyExpression;
    }

    @Override
    public IValue evaluate(IEnvironment<IValue> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            DivisionException {
        IEnvironment<IValue> definitionEnvironment = environment.beginScope();

        for (Triplet<String, IType, ASTNode> definition : definitions) {
            String definitionIdentifier = definition.getFirst();
            IValue definitionValue = definition.getThird().evaluate(environment);
            definitionEnvironment.bind(definitionIdentifier, definitionValue);
        }

        return bodyExpression.evaluate(definitionEnvironment);
    }

    @Override
    public IType typecheck(IEnvironment<IType> environment)
            throws UndeclaredIdentifierException, DuplicateIdentifierException,
            TypingException {
        IEnvironment<IType> definitionEnvironment = environment.beginScope();

        for (Triplet<String, IType, ASTNode> definition : definitions) {
            String definitionIdentifier = definition.getFirst();
            IType identifierType = definition.getSecond();
            IType definitionType = definition.getThird().typecheck(environment);

            if (!identifierType.equals(definitionType)) {
                throw new TypingException("Wrong types in declaration operation!");
            }

            definitionEnvironment.bind(definitionIdentifier, definitionType);
        }

        return expressionType = bodyExpression.typecheck(definitionEnvironment);
    }

    @Override
    public void assemble(IAssembler assembler) {
        String identifier = assembler.frame();

        assembler.dup();
        assembler.frameinvokespecial(identifier);
        assembler.dup();

        StackFrame current = assembler.aload();
        assembler.frameputstaticlink(identifier, current != null ? current.getIdentifier() : null);
        assembler.astore(identifier);

        for (Triplet<String, IType, ASTNode> definition : definitions) {
            String definitionIdentifier = definition.getFirst();
            IType identifierType = definition.getSecond();

            assembler.aload();
            definition.getThird().assemble(assembler);
            assembler.frameputfield(identifier, definitionIdentifier, identifierType);

        }

        bodyExpression.assemble(assembler);

        StackFrame ancestor = assembler.aload().getAncestor();
        assembler.framegetstaticlink(identifier, ancestor != null ? ancestor.getIdentifier() : null);
        assembler.astore(ancestor != null ? ancestor.getIdentifier() : null);
    }

    @Override
    public IType getType() {
        return expressionType;
    }
}
