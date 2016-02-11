/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

/**
 *
 * @author Ryan
 */
public class Token {
    
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
        LPAREN_TOKEN,
        RPAREN_TOKEN,
        LSBRACK_TOKEN,
        RSBRACK_TOKEN,
        LCBRACK_TOKEN,
        RCBRACK_TOKEN,
        BEGIN_COMMENT_TOKEN,
        END_COMMENT_TOKEN,
        ID_TOKEN,
        NUM_TOKEN,
        LETTER_TOKEN,
        DIGIT_TOKEN
    }

    private TokenType tokenType;
    private Object tokenData;

    public Token (TokenType type) {
        this (type, null);
    }

    public Token (TokenType type, Object data) {
        tokenType = type;
        tokenData = data;
    }

    // some access methods
    TokenType getTokenType() {
        return tokenType;
    }
    
    Object getTokenData() {
        return tokenData;
    }
    
    void setTokenType(TokenType type) {
        tokenType = type;
    }
    
    void setTokenData(Object data) {
        tokenData = data;
    }
}

