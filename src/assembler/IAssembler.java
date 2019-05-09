package assembler;

import type.IType;

import java.io.IOException;

public interface IAssembler {

    String getLabel();

    void sipush(int integer);

    void iconst(boolean bool);

    void iadd();

    void isub();

    void imul();

    void idiv();

    void iand();

    void ior();

    void ineg();

    void ifeq(String label);

    void ifne(String label);

    void if_icmpeq(String label);

    void if_icmpne(String label);

    void if_icmplt(String label);

    void if_icmple(String label);

    void if_icmpgt(String label);

    void if_icmpge(String label);

    void jump(String label);

    void anchor(String label);

    void dup();

    void pop();

    StackFrame aload();

    void astore(String identifier);

    String reference(IType type);

    void referenceinvokespecial(IType type);

    void referencecheckcast(IType type);

    void referenceputfield(IType type);

    void referencegetfield(IType type);

    String frame();

    void frameinvokespecial(String identifier);

    void frameputstaticlink(String identifier, String ancestor);

    void framegetstaticlink(String identifier, String ancestor);

    void frameputfield(String identifier, String definition, IType type);

    void framegetfield(String identifier, String name, IType type);

    String dump() throws IOException;
}
