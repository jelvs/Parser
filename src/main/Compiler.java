package main;

import assembler.Assembler;
import assembler.IAssembler;
import ast.ASTNode;
import environment.Environment;
import exception.DuplicateIdentifierException;
import exception.TypingException;
import exception.UndeclaredIdentifierException;
import parser.ParseException;
import parser.Parser;
import parser.TokenMgrError;
import type.IType;

import java.io.IOException;

public class Compiler {

    public static void main(String args[]) {
        Parser parser = new Parser(System.in);

        while (true) {
            try {
                ASTNode root = parser.Start();
                IAssembler assembler = new Assembler();
                root.typecheck(new Environment<IType>());
                root.assemble(assembler);
                System.out.println(String.format("Files generated successfully at %s!", assembler.dump()));
            } catch (TokenMgrError e) {
                System.out.println("Lexical Error!");
            } catch (ParseException e) {
                System.out.println("Syntax Error!");
            } catch (UndeclaredIdentifierException | DuplicateIdentifierException
                    | TypingException | IOException e) {
                System.out.println(e.getMessage());
            } finally {
                Parser.ReInit(System.in);
            }
        }
    }
}
