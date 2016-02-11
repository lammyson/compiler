/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.io.BufferedReader;
import scanner.Token.TokenType;

/**
 *
 * @author Ryan
 */
public class CMinusScanner implements Scanner{
    
    /**
     * Enum used to determine what state you are in
     */
    public enum StateType {
        START,
        INNUM,
        INID,
        INASSIGN,
        INCOMMENT,
        INLESSTHAN,
        INGREATERTHAN,
        DONE,
    }
    
    private BufferedReader inFile;
    private Token nextToken;
    
    public CMinusScanner(BufferedReader file) {
        inFile = file;
        nextToken = scanToken();
    }
    
    public Token getNextToken() {
        Token returnToken = nextToken;
        if (nextToken.getTokenType() != Token.TokenType.EOF_TOKEN) {
            nextToken = scanToken();
        }
        return returnToken;
    }
    
    public Token viewNextToken() {
        return nextToken;
    }
    
    public Token scanToken() {
        int tokenStringIndex = 0;
        TokenType currentToken = null;
        StateType state = StateType.START;
        int save;
        
        return null;
    }
               
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}