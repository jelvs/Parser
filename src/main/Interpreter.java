package main;

import ast.ASTNode;
import environment.Environment;
import exception.DivisionException;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import parser.ParseException;
import parser.Parser;
import parser.TokenMgrError;
import type.IType;
import value.BooleanValue;
import value.IValue;
import value.IntegerValue;

public class Interpreter {
    public static void main(String args[]) {
        Parser parser = new Parser(System.in);

        while (true) {
            try {
                ASTNode root = parser.Start();
                root.typecheck(new Environment<IType>());
                IValue value = root.evaluate(new Environment<IValue>());

                if (value instanceof IntegerValue) {
                    System.out.println(String.format("Result: %d!", ((IntegerValue) value).getValue()));
                } else if (value instanceof BooleanValue) {
                    System.out.println(String.format("Result: %b!", ((BooleanValue) value).getValue()));
                } else {
                    System.out.println(String.format("Result: %s!", value.getClass().getCanonicalName()));
                }
            } catch (TokenMgrError e) {
                System.out.println("Lexical Error!");
            } catch (ParseException e) {
                System.out.println("Syntax Error!");
            } catch (UndeclaredIdentifierException | DuplicateIdentifierException
                    | TypingException | DivisionException e) {
                System.out.println(e.getMessage());
            } finally {
                Parser.ReInit(System.in);
            }
        }
    }
}
