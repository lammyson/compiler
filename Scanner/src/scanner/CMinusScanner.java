/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.io.BufferedReader;
import java.io.IOException;
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
        boolean save;
        while (state != StateType.DONE) {
            try {
                char c = (char)inFile.read();
                save = true;
                switch (state) {
                    case START:
                        if (Character.isDigit(c)) {
                            state = StateType.INNUM;
                        } else if (Character.isAlphabetic((int)c)) {
                            state = StateType.INID;
                        } else if (c == '=') {
                            state = StateType.INASSIGN;
                        } else if (c == '<') {
                            state = StateType.INLESSTHAN;
                        } else if (c == '>') {
                            state = StateType.INGREATERTHAN;
                        } else if ((c == ' ') || (c == '\t') || (c == '\n')) {
                            save = false;
                        } else if (c == '/') {
                            state = StateType.INCOMMENT;
                        } else {
                            state = StateType.DONE;
                            switch (c) {
                                case 0x3:   //Supposedly the eof character
                                    save = false;
                                    currentToken = Token.TokenType.EOF_TOKEN;
                                    break;
                                case '+':
                                    currentToken = Token.TokenType.ADD_TOKEN;
                                    break;
                                case '-':
                                    currentToken = Token.TokenType.SUB_TOKEN;
                                    break;
                                case '*':
                                    currentToken = Token.TokenType.MUL_TOKEN;
                                    break;
                                case '(':
                                    currentToken = Token.TokenType.LPAREN_TOKEN;
                                    break;
                                case ')':
                                    currentToken = Token.TokenType.RPAREN_TOKEN;
                                    break;
                                case '[':
                                    currentToken = Token.TokenType.LSBRACK_TOKEN;
                                    break;
                                case ']':
                                    currentToken = Token.TokenType.RSBRACK_TOKEN;
                                    break;
                                case '{':
                                    currentToken = Token.TokenType.LCBRACK_TOKEN;
                                    break;
                                case '}':
                                    currentToken = Token.TokenType.RCBRACK_TOKEN;
                                    break;
                                case ';':
                                    currentToken = Token.TokenType.SEMI_TOKEN;
                                    break;
                            }
                        }
                    break;
                    case INCOMMENT:
                        if (c == '*') {
                            
                        }
                        
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
        }
        
        return null;
    }
               
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}