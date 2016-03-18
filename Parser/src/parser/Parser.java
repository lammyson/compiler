package parser;

import java.util.ArrayList;
import scanner.CMinusScanner;
import scanner.Token.TokenType;
/**
 *
 * @author Ryan
 */
public class Parser {
    CMinusScanner scan;
    Program       p;
    
    public Parser(CMinusScanner s) {
        scan = s;
    }
    
    /**
     * 
     * @return 
     */
    public Program parseProgram() {
        ArrayList<Declaration> declList = new ArrayList();
        TokenType type = scan.viewNextToken().getTokenType();
        while (type == TokenType.INT_TOKEN || type == TokenType.VOID_TOKEN) {
            declList.add(parseDeclaration());
        }
        if (scan.viewNextToken().getTokenType() != TokenType.EOF_TOKEN) {
            throw new ParseError("Error in parseProgram: expected EOF");
        }
        
        return new Program(declList);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
