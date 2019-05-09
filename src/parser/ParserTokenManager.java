/* Generated By:JavaCC: Do not edit this line. ParserTokenManager.java */
package parser;

/**
 * Token Manager.
 */
public class ParserTokenManager implements ParserConstants {

    /**
     * Token literal values.
     */
    public static final String[] jjstrLiteralImages = {
            "", null, null, null, null, null, "\151\156\164", "\142\157\157\154",
            "\162\145\146", "\164\162\165\145", "\146\141\154\163\145", "\41", "\73", "\72",
            "\146\165\156", "\55\76", "\164\150\145\156", "\144\157", "\151\146", "\145\154\163\145",
            "\167\150\151\154\145", "\156\145\167", "\76", "\76\75", "\74", "\74\75", "\154\145\164", "\72\75",
            "\75", "\75\75", "\41\75", "\174\174", "\46\46", "\176", "\54", "\151\156",
            "\145\156\144", "\53", "\55", "\52", "\57", "\50", "\51", null, null, "\73\73",};
    /**
     * Lexer state names.
     */
    public static final String[] lexStateNames = {
            "DEFAULT",
    };
    static final int[] jjnextStates = {
    };
    static final long[] jjtoToken = {
            0x3fffffffffc1L,
    };
    static final long[] jjtoSkip = {
            0x3eL,
    };
    static private final int[] jjrounds = new int[3];
    static private final int[] jjstateSet = new int[6];
    /**
     * Debug output.
     */
    public static java.io.PrintStream debugStream = System.out;
    static protected SimpleCharStream input_stream;
    static protected char curChar;
    static int curLexState = 0;
    static int defaultLexState = 0;
    static int jjnewStateCnt;
    static int jjround;
    static int jjmatchedPos;
    static int jjmatchedKind;

    /**
     * Constructor.
     */
    public ParserTokenManager(SimpleCharStream stream) {
        if (input_stream != null)
            throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
        input_stream = stream;
    }

    /**
     * Constructor.
     */
    public ParserTokenManager(SimpleCharStream stream, int lexState) {
        this(stream);
        SwitchTo(lexState);
    }

    /**
     * Set debug output.
     */
    public static void setDebugStream(java.io.PrintStream ds) {
        debugStream = ds;
    }

    private static final int jjStopStringLiteralDfa_0(int pos, long active0) {
        switch (pos) {
            case 0:
                if ((active0 & 0x18043f47c0L) != 0L) {
                    jjmatchedKind = 44;
                    return 2;
                }
                return -1;
            case 1:
                if ((active0 & 0x800060040L) != 0L)
                    return 2;
                if ((active0 & 0x1004394780L) != 0L) {
                    if (jjmatchedPos != 1) {
                        jjmatchedKind = 44;
                        jjmatchedPos = 1;
                    }
                    return 2;
                }
                return -1;
            case 2:
                if ((active0 & 0x1004204140L) != 0L)
                    return 2;
                if ((active0 & 0x190680L) != 0L) {
                    jjmatchedKind = 44;
                    jjmatchedPos = 2;
                    return 2;
                }
                return -1;
            case 3:
                if ((active0 & 0x90280L) != 0L)
                    return 2;
                if ((active0 & 0x100400L) != 0L) {
                    jjmatchedKind = 44;
                    jjmatchedPos = 3;
                    return 2;
                }
                return -1;
            default:
                return -1;
        }
    }

    private static final int jjStartNfa_0(int pos, long active0) {
        return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }

    static private int jjStopAtPos(int pos, int kind) {
        jjmatchedKind = kind;
        jjmatchedPos = pos;
        return pos + 1;
    }

    static private int jjMoveStringLiteralDfa0_0() {
        switch (curChar) {
            case 33:
                jjmatchedKind = 11;
                return jjMoveStringLiteralDfa1_0(0x40000000L);
            case 38:
                return jjMoveStringLiteralDfa1_0(0x100000000L);
            case 40:
                return jjStopAtPos(0, 41);
            case 41:
                return jjStopAtPos(0, 42);
            case 42:
                return jjStopAtPos(0, 39);
            case 43:
                return jjStopAtPos(0, 37);
            case 44:
                return jjStopAtPos(0, 34);
            case 45:
                jjmatchedKind = 38;
                return jjMoveStringLiteralDfa1_0(0x8000L);
            case 47:
                return jjStopAtPos(0, 40);
            case 58:
                jjmatchedKind = 13;
                return jjMoveStringLiteralDfa1_0(0x8000000L);
            case 59:
                jjmatchedKind = 12;
                return jjMoveStringLiteralDfa1_0(0x200000000000L);
            case 60:
                jjmatchedKind = 24;
                return jjMoveStringLiteralDfa1_0(0x2000000L);
            case 61:
                jjmatchedKind = 28;
                return jjMoveStringLiteralDfa1_0(0x20000000L);
            case 62:
                jjmatchedKind = 22;
                return jjMoveStringLiteralDfa1_0(0x800000L);
            case 98:
                return jjMoveStringLiteralDfa1_0(0x80L);
            case 100:
                return jjMoveStringLiteralDfa1_0(0x20000L);
            case 101:
                return jjMoveStringLiteralDfa1_0(0x1000080000L);
            case 102:
                return jjMoveStringLiteralDfa1_0(0x4400L);
            case 105:
                return jjMoveStringLiteralDfa1_0(0x800040040L);
            case 108:
                return jjMoveStringLiteralDfa1_0(0x4000000L);
            case 110:
                return jjMoveStringLiteralDfa1_0(0x200000L);
            case 114:
                return jjMoveStringLiteralDfa1_0(0x100L);
            case 116:
                return jjMoveStringLiteralDfa1_0(0x10200L);
            case 119:
                return jjMoveStringLiteralDfa1_0(0x100000L);
            case 124:
                return jjMoveStringLiteralDfa1_0(0x80000000L);
            case 126:
                return jjStopAtPos(0, 33);
            default:
                return jjMoveNfa_0(1, 0);
        }
    }

    static private int jjMoveStringLiteralDfa1_0(long active0) {
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
        switch (curChar) {
            case 38:
                if ((active0 & 0x100000000L) != 0L)
                    return jjStopAtPos(1, 32);
                break;
            case 59:
                if ((active0 & 0x200000000000L) != 0L)
                    return jjStopAtPos(1, 45);
                break;
            case 61:
                if ((active0 & 0x800000L) != 0L)
                    return jjStopAtPos(1, 23);
                else if ((active0 & 0x2000000L) != 0L)
                    return jjStopAtPos(1, 25);
                else if ((active0 & 0x8000000L) != 0L)
                    return jjStopAtPos(1, 27);
                else if ((active0 & 0x20000000L) != 0L)
                    return jjStopAtPos(1, 29);
                else if ((active0 & 0x40000000L) != 0L)
                    return jjStopAtPos(1, 30);
                break;
            case 62:
                if ((active0 & 0x8000L) != 0L)
                    return jjStopAtPos(1, 15);
                break;
            case 97:
                return jjMoveStringLiteralDfa2_0(active0, 0x400L);
            case 101:
                return jjMoveStringLiteralDfa2_0(active0, 0x4200100L);
            case 102:
                if ((active0 & 0x40000L) != 0L)
                    return jjStartNfaWithStates_0(1, 18, 2);
                break;
            case 104:
                return jjMoveStringLiteralDfa2_0(active0, 0x110000L);
            case 108:
                return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
            case 110:
                if ((active0 & 0x800000000L) != 0L) {
                    jjmatchedKind = 35;
                    jjmatchedPos = 1;
                }
                return jjMoveStringLiteralDfa2_0(active0, 0x1000000040L);
            case 111:
                if ((active0 & 0x20000L) != 0L)
                    return jjStartNfaWithStates_0(1, 17, 2);
                return jjMoveStringLiteralDfa2_0(active0, 0x80L);
            case 114:
                return jjMoveStringLiteralDfa2_0(active0, 0x200L);
            case 117:
                return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
            case 124:
                if ((active0 & 0x80000000L) != 0L)
                    return jjStopAtPos(1, 31);
                break;
            default:
                break;
        }
        return jjStartNfa_0(0, active0);
    }

    static private int jjMoveStringLiteralDfa2_0(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_0(0, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_0(1, active0);
            return 2;
        }
        switch (curChar) {
            case 100:
                if ((active0 & 0x1000000000L) != 0L)
                    return jjStartNfaWithStates_0(2, 36, 2);
                break;
            case 101:
                return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
            case 102:
                if ((active0 & 0x100L) != 0L)
                    return jjStartNfaWithStates_0(2, 8, 2);
                break;
            case 105:
                return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
            case 108:
                return jjMoveStringLiteralDfa3_0(active0, 0x400L);
            case 110:
                if ((active0 & 0x4000L) != 0L)
                    return jjStartNfaWithStates_0(2, 14, 2);
                break;
            case 111:
                return jjMoveStringLiteralDfa3_0(active0, 0x80L);
            case 115:
                return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
            case 116:
                if ((active0 & 0x40L) != 0L)
                    return jjStartNfaWithStates_0(2, 6, 2);
                else if ((active0 & 0x4000000L) != 0L)
                    return jjStartNfaWithStates_0(2, 26, 2);
                break;
            case 117:
                return jjMoveStringLiteralDfa3_0(active0, 0x200L);
            case 119:
                if ((active0 & 0x200000L) != 0L)
                    return jjStartNfaWithStates_0(2, 21, 2);
                break;
            default:
                break;
        }
        return jjStartNfa_0(1, active0);
    }

    static private int jjMoveStringLiteralDfa3_0(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_0(1, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_0(2, active0);
            return 3;
        }
        switch (curChar) {
            case 101:
                if ((active0 & 0x200L) != 0L)
                    return jjStartNfaWithStates_0(3, 9, 2);
                else if ((active0 & 0x80000L) != 0L)
                    return jjStartNfaWithStates_0(3, 19, 2);
                break;
            case 108:
                if ((active0 & 0x80L) != 0L)
                    return jjStartNfaWithStates_0(3, 7, 2);
                return jjMoveStringLiteralDfa4_0(active0, 0x100000L);
            case 110:
                if ((active0 & 0x10000L) != 0L)
                    return jjStartNfaWithStates_0(3, 16, 2);
                break;
            case 115:
                return jjMoveStringLiteralDfa4_0(active0, 0x400L);
            default:
                break;
        }
        return jjStartNfa_0(2, active0);
    }

    static private int jjMoveStringLiteralDfa4_0(long old0, long active0) {
        if (((active0 &= old0)) == 0L)
            return jjStartNfa_0(2, old0);
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            jjStopStringLiteralDfa_0(3, active0);
            return 4;
        }
        switch (curChar) {
            case 101:
                if ((active0 & 0x400L) != 0L)
                    return jjStartNfaWithStates_0(4, 10, 2);
                else if ((active0 & 0x100000L) != 0L)
                    return jjStartNfaWithStates_0(4, 20, 2);
                break;
            default:
                break;
        }
        return jjStartNfa_0(3, active0);
    }

    static private int jjStartNfaWithStates_0(int pos, int kind, int state) {
        jjmatchedKind = kind;
        jjmatchedPos = pos;
        try {
            curChar = input_stream.readChar();
        } catch (java.io.IOException e) {
            return pos + 1;
        }
        return jjMoveNfa_0(state, pos + 1);
    }

    static private int jjMoveNfa_0(int startState, int curPos) {
        int startsAt = 0;
        jjnewStateCnt = 3;
        int i = 1;
        jjstateSet[0] = startState;
        int kind = 0x7fffffff;
        for (; ; ) {
            if (++jjround == 0x7fffffff)
                ReInitRounds();
            if (curChar < 64) {
                long l = 1L << curChar;
                do {
                    switch (jjstateSet[--i]) {
                        case 1:
                        case 0:
                            if ((0x3ff000000000000L & l) == 0L)
                                break;
                            if (kind > 43)
                                kind = 43;
                            jjCheckNAdd(0);
                            break;
                        case 2:
                            if ((0x3ff000000000000L & l) == 0L)
                                break;
                            if (kind > 44)
                                kind = 44;
                            jjstateSet[jjnewStateCnt++] = 2;
                            break;
                        default:
                            break;
                    }
                } while (i != startsAt);
            } else if (curChar < 128) {
                long l = 1L << (curChar & 077);
                do {
                    switch (jjstateSet[--i]) {
                        case 1:
                        case 2:
                            if ((0x7fffffe07fffffeL & l) == 0L)
                                break;
                            if (kind > 44)
                                kind = 44;
                            jjCheckNAdd(2);
                            break;
                        default:
                            break;
                    }
                } while (i != startsAt);
            } else {
                int i2 = (curChar & 0xff) >> 6;
                long l2 = 1L << (curChar & 077);
                do {
                    switch (jjstateSet[--i]) {
                        default:
                            break;
                    }
                } while (i != startsAt);
            }
            if (kind != 0x7fffffff) {
                jjmatchedKind = kind;
                jjmatchedPos = curPos;
                kind = 0x7fffffff;
            }
            ++curPos;
            if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
                return curPos;
            try {
                curChar = input_stream.readChar();
            } catch (java.io.IOException e) {
                return curPos;
            }
        }
    }

    /**
     * Reinitialise parser.
     */
    static public void ReInit(SimpleCharStream stream) {
        jjmatchedPos = jjnewStateCnt = 0;
        curLexState = defaultLexState;
        input_stream = stream;
        ReInitRounds();
    }

    static private void ReInitRounds() {
        int i;
        jjround = 0x80000001;
        for (i = 3; i-- > 0; )
            jjrounds[i] = 0x80000000;
    }

    /**
     * Reinitialise parser.
     */
    static public void ReInit(SimpleCharStream stream, int lexState) {
        ReInit(stream);
        SwitchTo(lexState);
    }

    /**
     * Switch to specified lex state.
     */
    static public void SwitchTo(int lexState) {
        if (lexState >= 1 || lexState < 0)
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
        else
            curLexState = lexState;
    }

    static protected Token jjFillToken() {
        final Token t;
        final String curTokenImage;
        final int beginLine;
        final int endLine;
        final int beginColumn;
        final int endColumn;
        String im = jjstrLiteralImages[jjmatchedKind];
        curTokenImage = (im == null) ? input_stream.GetImage() : im;
        beginLine = input_stream.getBeginLine();
        beginColumn = input_stream.getBeginColumn();
        endLine = input_stream.getEndLine();
        endColumn = input_stream.getEndColumn();
        t = Token.newToken(jjmatchedKind, curTokenImage);

        t.beginLine = beginLine;
        t.endLine = endLine;
        t.beginColumn = beginColumn;
        t.endColumn = endColumn;

        return t;
    }

    /**
     * Get the next Token.
     */
    public static Token getNextToken() {
        Token matchedToken;
        int curPos = 0;

        EOFLoop:
        for (; ; ) {
            try {
                curChar = input_stream.BeginToken();
            } catch (java.io.IOException e) {
                jjmatchedKind = 0;
                matchedToken = jjFillToken();
                return matchedToken;
            }

            try {
                input_stream.backup(0);
                while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
                    curChar = input_stream.BeginToken();
            } catch (java.io.IOException e1) {
                continue EOFLoop;
            }
            jjmatchedKind = 0x7fffffff;
            jjmatchedPos = 0;
            curPos = jjMoveStringLiteralDfa0_0();
            if (jjmatchedKind != 0x7fffffff) {
                if (jjmatchedPos + 1 < curPos)
                    input_stream.backup(curPos - jjmatchedPos - 1);
                if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L) {
                    matchedToken = jjFillToken();
                    return matchedToken;
                } else {
                    continue EOFLoop;
                }
            }
            int error_line = input_stream.getEndLine();
            int error_column = input_stream.getEndColumn();
            String error_after = null;
            boolean EOFSeen = false;
            try {
                input_stream.readChar();
                input_stream.backup(1);
            } catch (java.io.IOException e1) {
                EOFSeen = true;
                error_after = curPos <= 1 ? "" : input_stream.GetImage();
                if (curChar == '\n' || curChar == '\r') {
                    error_line++;
                    error_column = 0;
                } else
                    error_column++;
            }
            if (!EOFSeen) {
                input_stream.backup(1);
                error_after = curPos <= 1 ? "" : input_stream.GetImage();
            }
            throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
        }
    }

    static private void jjCheckNAdd(int state) {
        if (jjrounds[state] != jjround) {
            jjstateSet[jjnewStateCnt++] = state;
            jjrounds[state] = jjround;
        }
    }

    static private void jjAddStates(int start, int end) {
        do {
            jjstateSet[jjnewStateCnt++] = jjnextStates[start];
        } while (start++ != end);
    }

    static private void jjCheckNAddTwoStates(int state1, int state2) {
        jjCheckNAdd(state1);
        jjCheckNAdd(state2);
    }

}
