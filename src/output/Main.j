.class public Main
.super java/lang/Object

.method public <init>()V
  aload_0
  invokenonvirtual java/lang/Object/<init>()V
  return
.end method

.method public static main([Ljava/lang/String;)V
  ; set limits used by this method
  .limit locals 10
  .limit stack 256

  ; 1 - the PrintStream object held in java.lang.out
  getstatic java/lang/System/out Ljava/io/PrintStream;

  ; START

  sipush 5
  sipush 5
  iadd

  ; END

  ; convert to String;
  invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
  ; call println 
  invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

   return

.end method
