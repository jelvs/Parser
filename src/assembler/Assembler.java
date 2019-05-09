package assembler;

import type.BooleanType;
import type.IType;
import type.IntegerType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Assembler implements IAssembler {

    private static final String SYSTEM_PATH = System.getProperty("user.dir") + File.separator;
    private static final String FOLDER_PATH = "src" + File.separator + "output" + File.separator;
    private static final String DEPLOY_PATH = SYSTEM_PATH + FOLDER_PATH;
    private static final String ASSEMBLY_NAME = "Main";
    private static final String STATIC_LINK_SUFFIX = "1";
    private static final String LABEL_PREFIX = "Label";
    private static final String REFERENCE_INT_NAME = "ref_int";
    private static final String REFERENCE_CLASS_NAME = "ref_class";
    private static final String FRAME_PREFIX = "frame_";

    private int counter;
    private StackFrame current;
    private List<String> instructions;
    private List<StackFrame> frames;

    public Assembler() {
        this.counter = 0;
        this.current = null;
        this.instructions = new ArrayList<String>();
        this.frames = new ArrayList<StackFrame>();
    }

    public String getLabel() {
        String label = LABEL_PREFIX + counter;
        counter++;
        return label;
    }

    public void sipush(int integer) {
        instructions.add(String.format("sipush %d", integer));
    }

    public void iconst(boolean bool) {
        if (!bool) {
            instructions.add("iconst_0");
        } else {
            instructions.add("iconst_1");
        }
    }

    public void iadd() {
        instructions.add("iadd");
    }

    public void isub() {
        instructions.add("isub");
    }

    public void imul() {
        instructions.add("imul");
    }

    public void idiv() {
        instructions.add("idiv");
    }

    public void iand() {
        instructions.add("iand");
    }

    public void ior() {
        instructions.add("ior");
    }

    public void ineg() {
        instructions.add("ineg");
    }

    public void ifeq(String label) {
        instructions.add(String.format("ifeq %s", label));
    }

    public void ifne(String label) {
        instructions.add(String.format("ifne %s", label));
    }

    public void if_icmpeq(String label) {
        instructions.add(String.format("if_icmpeq %s", label));
    }

    public void if_icmpne(String label) {
        instructions.add(String.format("if_icmpne %s", label));
    }

    public void if_icmplt(String label) {
        instructions.add(String.format("if_icmplt %s", label));
    }

    public void if_icmple(String label) {
        instructions.add(String.format("if_icmple %s", label));
    }

    public void if_icmpgt(String label) {
        instructions.add(String.format("if_icmpgt %s", label));
    }

    public void if_icmpge(String label) {
        instructions.add(String.format("if_icmpge %s", label));
    }

    public void jump(String label) {
        instructions.add(String.format("goto %s", label));
    }

    public void anchor(String label) {
        instructions.add(String.format("%s:", label));
    }

    public void dup() {
        instructions.add("dup");
    }

    public void pop() {
        instructions.add("pop");
    }

    public StackFrame aload() {
        instructions.add(String.format("aload_%s", STATIC_LINK_SUFFIX));
        return current;
    }

    public void astore(String identifier) {
        instructions.add(String.format("astore_%s", STATIC_LINK_SUFFIX));
        current = getFrame(identifier);
    }

    public String reference(IType type) {
        String identifier;

        if (type.equals(IntegerType.getType()) || type.equals(BooleanType.getType())) {
            identifier = REFERENCE_INT_NAME;
        } else {
            identifier = REFERENCE_CLASS_NAME;
        }

        instructions.add(String.format("new %s", identifier));
        return identifier;
    }

    public void referenceinvokespecial(IType type) {
        if (type.equals(IntegerType.getType()) || type.equals(BooleanType.getType())) {
            instructions.add(String.format("invokespecial %s/<init>()V", REFERENCE_INT_NAME));
        } else {
            instructions.add(String.format("invokespecial %s/<init>()V", REFERENCE_CLASS_NAME));
        }
    }

    @Override
    public void referencecheckcast(IType type) {
        if (type.equals(IntegerType.getType()) || type.equals(BooleanType.getType())) {
            instructions.add(String.format("checkcast %s", REFERENCE_INT_NAME));
        } else {
            instructions.add(String.format("checkcast %s", REFERENCE_CLASS_NAME));
        }
    }

    public void referenceputfield(IType type) {
        if (type.equals(IntegerType.getType()) || type.equals(BooleanType.getType())) {
            instructions.add(String.format("putfield %s/value I", REFERENCE_INT_NAME));
        } else {
            instructions.add(String.format("putfield %s/value Ljava/lang/Object;", REFERENCE_CLASS_NAME));
        }
    }

    @Override
    public void referencegetfield(IType type) {
        if (type.equals(IntegerType.getType()) || type.equals(BooleanType.getType())) {
            instructions.add(String.format("getfield %s/value I", REFERENCE_INT_NAME));
        } else {
            instructions.add(String.format("getfield %s/value Ljava/lang/Object;", REFERENCE_CLASS_NAME));
        }
    }

    public String frame() {
        String identifier = FRAME_PREFIX + frames.size();
        instructions.add(String.format("new %s", identifier));
        frames.add(new StackFrame(identifier));
        return identifier;
    }

    @Override
    public void frameinvokespecial(String identifier) {
        instructions.add(String.format("invokespecial %s/<init>()V", identifier));
    }

    @Override
    public void frameputstaticlink(String identifier, String ancestor) {
        StackFrame frame = getFrame(identifier);

        if (frame != null) {
            if (ancestor != null) {
                frame.setAncestor(getFrame(ancestor));
                instructions.add(String.format("putfield %s/sl L%s;", identifier, ancestor));
            } else {
                instructions.add(String.format("putfield %s/sl Ljava/lang/Object;", identifier));
            }
        }
    }

    @Override
    public void framegetstaticlink(String identifier, String ancestor) {
        if (ancestor != null) {
            instructions.add(String.format("getfield %s/sl L%s;", identifier, ancestor));
        } else {
            instructions.add(String.format("getfield %s/sl Ljava/lang/Object;", identifier));
        }
    }

    @Override
    public void frameputfield(String identifier, String definition, IType type) {
        StackFrame frame = getFrame(identifier);

        if (frame != null) {
            String name = frame.addField(definition, type);

            if (type.equals(IntegerType.getType()) || type.equals(BooleanType.getType())) {
                instructions.add(String.format("putfield %s/%s I", identifier, name));
            } else {
                instructions.add(String.format("putfield %s/%s Ljava/lang/Object;", identifier, name));
            }
        }
    }

    @Override
    public void framegetfield(String identifier, String name, IType type) {
        if (type.equals(IntegerType.getType()) || type.equals(BooleanType.getType())) {
            instructions.add(String.format("getfield %s/%s I", identifier, name));
        } else {
            instructions.add(String.format("getfield %s/%s Ljava/lang/Object;", identifier, name));
        }
    }

    public String dump() throws IOException {
        File outputFolder = new File(DEPLOY_PATH);
        File[] files = outputFolder.listFiles();

        if (files != null) {
            for (File f : files) {
                Files.deleteIfExists(f.toPath());
            }

            createAssemblyFile();
            createReferenceIntFrame();
            createReferenceClassFrame();
            createStackFrames();
            return DEPLOY_PATH;
        }

        throw new IOException("Missing file or directory!");
    }

    private StackFrame getFrame(String identifier) {
        for (StackFrame frame : frames) {
            if (frame.getIdentifier().equals(identifier)) {
                return frame;
            }
        }

        return null;
    }

    private void createAssemblyFile() throws IOException {
        File f = new File(DEPLOY_PATH + ASSEMBLY_NAME + ".j");
        PrintStream out = new PrintStream(new FileOutputStream(f, false));

        out.println(".class public Main");
        out.println(".super java/lang/Object");
        out.println("");
        out.println(".method public <init>()V");
        out.println("  aload_0");
        out.println("  invokenonvirtual java/lang/Object/<init>()V");
        out.println("  return");
        out.println(".end method");
        out.println("");
        out.println(".method public static main([Ljava/lang/String;)V");
        out.println("  ; set limits used by this method");
        out.println("  .limit locals 10");
        out.println("  .limit stack 256");
        out.println("");
        out.println("  ; 1 - the PrintStream object held in java.lang.out");
        out.println("  getstatic java/lang/System/out Ljava/io/PrintStream;");
        out.println("");
        out.println("  ; START");
        out.println("");

        if (!frames.isEmpty()) {
            out.println("  aconst_null");
            out.println(String.format("  astore_%s", STATIC_LINK_SUFFIX));
            out.println("");
        }

        for (String instruction : instructions) {
            out.println(String.format("  %s", instruction));
        }

        out.println("");
        out.println("  ; END");
        out.println("");
        out.println("  ; convert to String;");
        out.println("  invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        out.println("  ; call println ");
        out.println("  invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        out.println("");
        out.println("   return");
        out.println("");
        out.println(".end method");

        out.close();
    }

    private void createReferenceIntFrame() throws IOException {
        File f = new File(DEPLOY_PATH + REFERENCE_INT_NAME + ".j");
        PrintStream out = new PrintStream(new FileOutputStream(f, false));

        out.println(String.format(".class %s", REFERENCE_INT_NAME));
        out.println(".super java/lang/Object");
        out.println(".field public value I");
        out.println("");
        out.println(".method public <init>()V");
        out.println("  aload_0");
        out.println("  invokenonvirtual java/lang/Object/<init>()V");
        out.println("  return");
        out.println(".end method");

        out.close();
    }

    private void createReferenceClassFrame() throws IOException {
        File f = new File(DEPLOY_PATH + REFERENCE_CLASS_NAME + ".j");
        PrintStream out = new PrintStream(new FileOutputStream(f, false));

        out.println(String.format(".class %s", REFERENCE_CLASS_NAME));
        out.println(".super java/lang/Object");
        out.println(".field public value Ljava/lang/Object;");
        out.println("");
        out.println(".method public <init>()V");
        out.println("  aload_0");
        out.println("  invokenonvirtual java/lang/Object/<init>()V");
        out.println("  return");
        out.println(".end method");

        out.close();
    }

    private void createStackFrames() throws IOException {
        for (StackFrame frame : frames) {
            File f = new File(DEPLOY_PATH + frame.getIdentifier() + ".j");
            PrintStream out = new PrintStream(new FileOutputStream(f, false));

            out.println(String.format(".class %s", frame.getIdentifier()));
            out.println(".super java/lang/Object");

            StackFrame previous = frame.getAncestor();

            if (previous == null) {
                out.println(".field public sl Ljava/lang/Object;");
            } else {
                out.println(String.format(".field public sl L%s;", previous.getIdentifier()));
            }

            for (StackVariable field : frame.getFields()) {
                if (field.getType().equals(IntegerType.getType()) || field.getType().equals(BooleanType.getType())) {
                    out.println(String.format(".field public %s I", field.getName()));
                } else {
                    out.println(String.format(".field public %s Ljava/lang/Object;", field.getName()));
                }
            }

            out.println("");
            out.println(".method public <init>()V");
            out.println("  aload_0");
            out.println("  invokenonvirtual java/lang/Object/<init>()V");
            out.println("  return");
            out.println(".end method");

            out.close();
        }
    }
}
