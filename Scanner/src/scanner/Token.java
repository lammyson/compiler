package scanner;

/**
 * This class will be used by the CMinusScanner class to help in scanning
 * for the tokens in a C Minus source code file
 */
public class Token {
    
    /**
     * TokenType enum to differentiate between different tokens while scanning
     */
    public enum TokenType {
        ELSE_TOKEN,
        IF_TOKEN,
        INT_TOKEN,
        RET_TOKEN,
        VOID_TOKEN,
        WHILE_TOKEN,
        ADD_TOKEN,
        SUB_TOKEN,
        MUL_TOKEN,
        DIV_TOKEN,
        LESS_TOKEN,
        LESS_EQ_TOKEN,
        GREAT_TOKEN,
        GREAT_EQ_TOKEN,
        EQUAL_TOKEN,
        NOT_EQ_TOKEN,
        ASSIGN_TOKEN,
        SEMI_TOKEN,
        COMMA_TOKEN,
        LPAREN_TOKEN,
        RPAREN_TOKEN,
        LSBRACK_TOKEN,
        RSBRACK_TOKEN,
        LCBRACK_TOKEN,
        RCBRACK_TOKEN,
        COMMENT_TOKEN,
        ID_TOKEN,
        NUM_TOKEN,
        EOF_TOKEN
    }

    //Class variables
    /**
     * Used to store the type of the token
     */
    private TokenType tokenType;
    
    /**
     * Used to store the actual data of the token, can be any type
     */
    private Object tokenData;

    /**
     * Constructors
     * @param type contains a token type defined in the enum
     */
    public Token (TokenType type) {
        this (type, null);
    }

    public Token (TokenType type, Object data) {
        tokenType = type;
        tokenData = data;
    }

    /**
     * Accessors 
     */
    TokenType getTokenType() {
        return tokenType;
    }
    
    Object getTokenData() {
        return tokenData;
    }
    
    /**
     * Mutators
     */
    void setTokenType(TokenType type) {
        tokenType = type;
    }
    
    void setTokenData(Object data) {
        tokenData = data;
    }
}

