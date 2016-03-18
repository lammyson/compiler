package parser;

import java.util.ArrayList;
import scanner.CMinusScanner;
import scanner.Token;
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
    private Program parseProgram() {
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
    
    private Declaration parseDeclaration() {
        Declaration decl;
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.INT_TOKEN) {
            matchToken(TokenType.INT_TOKEN);
            String id = matchIDToken();
            decl = parseDeclaration2(id);
        } else if (type == TokenType.VOID_TOKEN) {
            matchToken(TokenType.VOID_TOKEN);
            String id = matchIDToken();
            decl = parseFunDeclaration2(0, id);
        } else {
            throw new ParseError("Error in parseDeclaration: unexpected token" +
                    scan.viewNextToken().getTokenType());
        }
        return decl;
    }
    
    public Declaration parseDeclaration2(String id) {
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.SEMI_TOKEN) {
            matchToken(TokenType.SEMI_TOKEN);
            return new VarDeclaration(new IdExpression(id), -1);
        } else if (type == TokenType.LSBRACK_TOKEN) {
            matchToken(TokenType.LSBRACK_TOKEN);
            int num = matchNumToken();
            matchToken(TokenType.RSBRACK_TOKEN);
            matchToken(TokenType.SEMI_TOKEN);
            return new VarDeclaration(new IdExpression(id), num);
        } else if (type == TokenType.LPAREN_TOKEN) {
            return parseFunDeclaration2(1, id);
        } else {
            throw new ParseError("Error in ParseDeclaration2: unexpected token" 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    private Declaration parseFunDeclaration2(int returnType, String id) {
        TokenType type = scan.viewNextToken().getTokenType();
        matchToken(TokenType.LPAREN_TOKEN);
        ArrayList<Param> params = parseParams();
        matchToken(TokenType.RPAREN_TOKEN);
        CompoundStatement cstmt = parseCompoundStmt();
        return new FunDeclaration(returnType, new IdExpression(id),
                params, cstmt);
    }
    
    private Declaration parseVarDeclaration() {
        matchToken(TokenType.INT_TOKEN);
        String id = matchIDToken();
        if (scan.viewNextToken().getTokenType() == TokenType.LSBRACK_TOKEN) {
            matchToken(TokenType.LSBRACK_TOKEN);
            int num = matchNumToken();
            matchToken(TokenType.RSBRACK_TOKEN);
            matchToken(TokenType.SEMI_TOKEN);
            return new VarDeclaration(new IdExpression(id), num);
        } else {
            matchToken(TokenType.SEMI_TOKEN);
            return new VarDeclaration(new IdExpression(id), -1);
        }
    }
    
    private ArrayList<Param> parseParams() {
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.INT_TOKEN) {
            return parseParamList();
        } else if (type == TokenType.VOID_TOKEN) {
            matchToken(TokenType.VOID_TOKEN);
            return null;
        } else {
            throw new ParseError("Error in parseParams: unexpected token" 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    private ArrayList<Param> parseParamList() {
        ArrayList<Param> plist = new ArrayList<Param>();
        plist.add(parseParam());
        while (scan.viewNextToken().getTokenType() == TokenType.COMMA_TOKEN) {
            matchToken(TokenType.COMMA_TOKEN);
            plist.add(parseParam());
        }
        return plist;
    }
    
    private Param parseParam() {
        TokenType type = scan.viewNextToken().getTokenType();
        matchToken(TokenType.INT_TOKEN);
        String id = matchIDToken();
        if (type == TokenType.LSBRACK_TOKEN) {
            matchToken(TokenType.LSBRACK_TOKEN);
            matchToken(TokenType.RSBRACK_TOKEN);
            return new Param(new IdExpression(id), true);
        } else if (type == TokenType.COMMA_TOKEN || 
                   type == TokenType.RPAREN_TOKEN) {
            return new Param(new IdExpression(id), false);
        } else {
            throw new ParseError("Error in parseParam: unexpected token" 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    
    
    private void matchToken(TokenType type) {
        if (scan.viewNextToken().getTokenType() == type) {
            
            scan.getNextToken();
        } else {
            throw new ParseError("matchToken error: expected " + type + 
                    " and found " + scan.viewNextToken().getTokenType());
        }
    }
    
    private String matchIDToken() {
        String str;
        Token nextToken = scan.viewNextToken();
        if (nextToken.getTokenType() == TokenType.ID_TOKEN) {
            str = nextToken.getTokenData().toString();
            scan.getNextToken();
        } else {
            throw new ParseError("matchToken error: expected TOKEN_ID" +  
                    " and found " + scan.viewNextToken().getTokenType());
        }
        return str;
    }
    
    private int matchNumToken() {
        int num;
        Token nextToken = scan.viewNextToken();
        if (nextToken.getTokenType() == TokenType.NUM_TOKEN) {
            num = Integer.parseInt(nextToken.getTokenData().toString());
            scan.getNextToken();
        } else {
            throw new ParseError("matchToken error: expected TOKEN_NUM" +  
                    " and found " + scan.viewNextToken().getTokenType());
        }
        return num;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
