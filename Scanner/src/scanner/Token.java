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

