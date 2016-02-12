package scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import scanner.Token.TokenType;

/**
 * This class implements the Scanner interface. It reads in source code from
 * a C Minus source code file and scans it to make sure there are no lexical
 * errors
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
        DONE
    }
    
    /**
     * Used to read in characters from a source code file
     */
    private BufferedReader inFile;
    
    /**
     * Used to store the next token in the source code file
     */
    private Token nextToken;
    
    /**
     * Constructor 
     * @param file is the file that will be scanned
     */
    public CMinusScanner(BufferedReader file) {
        inFile = file;
        nextToken = scanToken();
    }
    
    /**
     * This method gets the next token in the input file
     * @return nextToken in the input file
     */
    public Token getNextToken() {
        Token returnToken = nextToken;
        if (nextToken.getTokenType() != Token.TokenType.EOF_TOKEN) {
            nextToken = scanToken();
        }
        return returnToken;
    }
    
    /**
     * This method views the next token in the input file
     * @return nextToken without consuming input
     */
    public Token viewNextToken() {
        return nextToken;
    }
    
    /**
     * This method will scan characters until the next token is found or a 
     * lexical error is found
     * @return token which is the current token found
     */
    public Token scanToken() {
        TokenType currentToken = null;
        StateType state = StateType.START;
        boolean save;
        StringBuffer string = new StringBuffer();
        Token token = null;
        int readInt;
        // While not done reading in token, continue with process
        while (state != StateType.DONE) {
            try {
                // Mark the file to "unread" a character if needed
                if (inFile.markSupported()) {
                    inFile.mark(100);
                }
                readInt = inFile.read();
                char c = (char)readInt;
                // -1 signifies end of file
                if (readInt == -1) {
                    currentToken = Token.TokenType.EOF_TOKEN;
                    state = StateType.DONE;
                }
                save = false;
                switch (state) {
                    case START:
                        if (Character.isDigit(c)) {
                            state = StateType.INNUM;
                            save = true;
                        } else if (Character.isAlphabetic((int)c)) {
                            state = StateType.INID;
                            save = true;
                        } else if (c == '=') {
                            state = StateType.INASSIGN;
                        } else if (c == '<') {
                            state = StateType.INLESSTHAN;
                        } else if (c == '>') {
                            state = StateType.INGREATERTHAN;
                        // whitespace chars
                        } else if ((c == ' ') || (c == '\t') || 
                                   (c == '\n') || (c == '\r')) {
                            save = false;
                        // '/' could signify comment or division
                        } else if (c == '/') {
                            state = StateType.INDIVCOM;
                        } else if (c == '!') {
                            state = StateType.INNOTEQUAL;
                        // single character tokens
                        } else {
                            state = StateType.DONE;
                            switch (c) {
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
                                default:
                                    throw new LexError("Invalid symbol found");
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
                            currentToken = Token.TokenType.COMMENT_TOKEN;
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
                    case INNOTEQUAL:
                        state = StateType.DONE;
                        if (c == '=') {
                            currentToken = Token.TokenType.NOT_EQ_TOKEN;
                        } else {
                            // Throw error if ! found with no = following
                            inFile.reset();
                            throw new LexError("! found without =");
                        }
                        break;
                    case INNUM:
                        if (!Character.isDigit(c)) {
                            // Whitespace needed between NUMs and IDs
                            if (Character.isAlphabetic(c)) {
                                throw new LexError("Letter found in INNUM");
                            }
                            state = StateType.DONE;
                            currentToken = Token.TokenType.NUM_TOKEN;
                            inFile.reset();
                        } else {
                            save = true;
                        }
                        break;
                    case INID:
                        if (!Character.isAlphabetic(c)) {
                            // Whitespace needed between IDs and NUMs
                            if (Character.isDigit(c)) {
                                throw new LexError("Num found in INID");
                            }
                            state = StateType.DONE;
                            currentToken = Token.TokenType.ID_TOKEN;
                            inFile.reset();
                        } else {
                            save = true;
                        }
                        break;
                    case DONE:
                        break;
                    // Should never reach this default
                    default:
                        System.out.println("Scanner error. Was in state" + state);
                        state = StateType.DONE;
                        throw new LexError("Scanner error");
                }
                // If num or id, append char to string for value storage
                if (save) {
                    string.append(c);
                }
                if (state == StateType.DONE) {
                    if (currentToken == Token.TokenType.ID_TOKEN) {
                        //Compare string to reserved tokens
                        if (string != null && string.toString().equals("else")) {
                            currentToken = Token.TokenType.ELSE_TOKEN;
                        } else if (string != null && string.toString().equals("if")) {
                            currentToken = Token.TokenType.IF_TOKEN;
                        } else if (string != null && string.toString().equals("int")) {
                            currentToken = Token.TokenType.INT_TOKEN;
                        } else if (string != null && string.toString().equals("return")) {
                            currentToken = Token.TokenType.RET_TOKEN;
                        } else if (string != null && string.toString().equals("void")) {
                            currentToken = Token.TokenType.VOID_TOKEN;
                        } else if (string != null && string.toString().equals("while")) {
                            currentToken = Token.TokenType.WHILE_TOKEN;
                        }
                        // Create ID token with string as data
                        token = new Token(currentToken, (Object) string.toString());
                        
                    } else if (currentToken == Token.TokenType.NUM_TOKEN) {
                        // Create NUM token with string cast to int as data
                        Integer numTokenData = Integer.valueOf(string.toString());
                        token = new Token(currentToken, (Object) numTokenData);
                    } else {
                        // Create token with no data
                        token = new Token(currentToken);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return token;
    }
               
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Initialize input file and scanner
            File filename = new File(args[0]);
            FileReader fReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fReader);
            CMinusScanner cScan = new CMinusScanner(reader);
            
            // Initialize output file
            FileWriter writer = new FileWriter(args[1], true);
            Token currentToken;
            String output = "";
            
            // Continue through input file until eof token is hit
            while(cScan.viewNextToken().getTokenType() != Token.TokenType.EOF_TOKEN) {
                currentToken = cScan.getNextToken(); 
                // output token type
                output += currentToken.getTokenType().toString();
                // if ID or NUM, output data with token type in form token(data)
                if (currentToken.getTokenType() == Token.TokenType.NUM_TOKEN ||
                        currentToken.getTokenType() == Token.TokenType.ID_TOKEN) {
                    output += ("(" + currentToken.getTokenData().toString() + ")");
                }
                output += "\n";
            }
            writer.write(output);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Write to file failed");
        }
        
    }
}
