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
        INDIVCOM,
        INENDCOM,
        INLESSTHAN,
        INGREATERTHAN,
        INNOTEQUAL,
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
        StringBuffer string = null;
        
        while (state != StateType.DONE) {
            try {
                if (inFile.markSupported()) {
                    inFile.mark(Integer.MAX_VALUE);
                }
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
                            state = StateType.INDIVCOM;
                        } else if (c == '!') {
                            state = StateType.INNOTEQUAL;
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
                                case ',':
                                    currentToken = Token.TokenType.COMMA_TOKEN;
                                    break;
                            }
                        }
                        break;
                    case INDIVCOM:
                        if (c == '*') {
                            state = StateType.INCOMMENT;
                        } else {
                            state = StateType.DONE;
                            inFile.reset();
                            currentToken = Token.TokenType.DIV_TOKEN;
                        }
                        break;
                    case INCOMMENT:
                        if (c == '*') {
                            state = StateType.INENDCOM;
                        }
                        break;
                    case INENDCOM:
                        if (c == '/') {
                            state = StateType.DONE;
                        } else if (c == '*') {
                            state = StateType.INENDCOM;
                        } else {
                            state = StateType.INCOMMENT;
                        }
                        break;
                    case INASSIGN:
                        state = StateType.DONE;
                        if (c == '=') {
                            currentToken = Token.TokenType.EQUAL_TOKEN;
                        } else {
                            inFile.reset();
                            currentToken = Token.TokenType.ASSIGN_TOKEN;
                        }
                        break;
                    case INLESSTHAN:
                        state = StateType.DONE;
                        if (c == '=') {
                            currentToken = Token.TokenType.LESS_EQ_TOKEN;
                        } else {
                            inFile.reset();
                            currentToken = Token.TokenType.LESS_TOKEN;
                        }
                        break;
                    case INGREATERTHAN:
                        state = StateType.DONE;
                        if (c == '=') {
                            currentToken = Token.TokenType.GREAT_EQ_TOKEN;
                        } else {
                            inFile.reset();
                            currentToken = Token.TokenType.GREAT_TOKEN;
                        }
                        break;
                    case INNUM:
                        if (!Character.isDigit(c)) {
                            state = StateType.DONE;
                            inFile.reset();
                        }
                        break;
                    case INNOTEQUAL:
                        if (c == '=') {
                            state = StateType.DONE;
                            currentToken = Token.TokenType.NOT_EQ_TOKEN;
                        }
                        break;
                    case DONE:
                        break;
                    default:
                        System.out.println("Scanner error. Was in state" + state);
                        state = StateType.DONE;
                        throw new LexError("Scanner error");
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
