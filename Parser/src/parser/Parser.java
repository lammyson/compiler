package parser;

import java.util.ArrayList;
import scanner.CMinusScanner;
import scanner.Token;
import scanner.Token.TokenType;

/**
 * This class will implement a C Minus Parser
 * @author Ryan
 */
public class Parser {
    /**
     * This holds the C Minus Scanner
     */
    CMinusScanner scan;
    
    /**
     * Constructor
     * @param s 
     */
    public Parser(CMinusScanner s) {
        scan = s;
    }
    
    /**
     * This method parses a program
     * @return 
     */
    private Program parseProgram() {
        ArrayList<Declaration> declList = new ArrayList();
        TokenType type = scan.viewNextToken().getTokenType();
        while (type == TokenType.INT_TOKEN || type == TokenType.VOID_TOKEN) {
            declList.add(parseDeclaration());
            type = scan.viewNextToken().getTokenType();
        }
        if (scan.viewNextToken().getTokenType() != TokenType.EOF_TOKEN) {
            throw new ParseError("Error in parseProgram: expected EOF");
        }
        return new Program(declList);
    }
    
    /**
     * This method parses a declaration
     * @return 
     */
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
            throw new ParseError("Error in parseDeclaration: unexpected token " 
                    + scan.viewNextToken().getTokenType());
        }
        return decl;
    }
    
    /**
     * This method parses a declaration prime
     * @param id from parseDeclaration
     * @return 
     */
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
            throw new ParseError("Error in ParseDeclaration2: unexpected token " 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    /**
     * This method parses a fun-declaration prime
     * @param returnType to determine if it's a fun or var declaration
     * @param id for declaration name
     * @return 
     */
    private Declaration parseFunDeclaration2(int returnType, String id) {
        TokenType type = scan.viewNextToken().getTokenType();
        matchToken(TokenType.LPAREN_TOKEN);
        ArrayList<Param> params = parseParams();
        matchToken(TokenType.RPAREN_TOKEN);
        CompoundStatement cstmt = parseCompoundStmt();
        return new FunDeclaration(returnType, new IdExpression(id),
                params, cstmt);
    }
    
    /**
     * This method parses a var-declaration
     * @return 
     */
    private VarDeclaration parseVarDeclaration() {
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
    
    /**
     * This method parses a params
     * @return 
     */
    private ArrayList<Param> parseParams() {
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.INT_TOKEN) {
            return parseParamList();
        } else if (type == TokenType.VOID_TOKEN) {
            matchToken(TokenType.VOID_TOKEN);
            return null;
        } else {
            throw new ParseError("Error in parseParams: unexpected token " 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    /**
     * This method parses a param-list
     * @return 
     */
    private ArrayList<Param> parseParamList() {
        ArrayList<Param> plist = new ArrayList<Param>();
        plist.add(parseParam());
        while (scan.viewNextToken().getTokenType() == TokenType.COMMA_TOKEN) {
            matchToken(TokenType.COMMA_TOKEN);
            plist.add(parseParam());
        }
        return plist;
    }
    
    /**
     * This method parses a param
     * @return 
     */
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
            throw new ParseError("Error in parseParam: unexpected token " 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    private CompoundStatement parseCompoundStmt() {
        ArrayList<VarDeclaration> declList = new ArrayList<VarDeclaration>();
        ArrayList<Statement> stmtList = new ArrayList<Statement>();
        TokenType type = scan.viewNextToken().getTokenType();
        matchToken(TokenType.LCBRACK_TOKEN);
        while (type == TokenType.INT_TOKEN) {
            declList.add(parseVarDeclaration());
            type = scan.viewNextToken().getTokenType();
        }
        while (type == TokenType.LCBRACK_TOKEN ||
               type == TokenType.NUM_TOKEN ||
               type == TokenType.LPAREN_TOKEN ||
               type == TokenType.ID_TOKEN ||
               type == TokenType.SEMI_TOKEN ||
               type == TokenType.IF_TOKEN ||
               type == TokenType.WHILE_TOKEN ||
               type == TokenType.RET_TOKEN) {
            stmtList.add(parseStatement());
            type = scan.viewNextToken().getTokenType();
        }
        matchToken(TokenType.RCBRACK_TOKEN);
        return new CompoundStatement(declList, stmtList);
    }
    
    private Statement parseStatement() {
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.NUM_TOKEN ||
            type == TokenType.LPAREN_TOKEN ||
            type == TokenType.ID_TOKEN ||
            type == TokenType.SEMI_TOKEN) {
            return parseExpressionStmt();
        } else if (type == TokenType.LCBRACK_TOKEN) {
            return parseCompoundStmt();
        } else if (type == TokenType.IF_TOKEN) {
            return parseSelectionStmt();
        } else if (type == TokenType.WHILE_TOKEN) {
            return parseIterationStmt();
        } else if (type == TokenType.RET_TOKEN) {
            return parseReturnStmt();
        } else {
            throw new ParseError("Error in parseStatment: unexpected token " 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    private ExpressionStatement parseExpressionStmt() {
        Expression expr = null;
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.NUM_TOKEN ||
            type == TokenType.LPAREN_TOKEN ||
            type == TokenType.ID_TOKEN) {
            expr = parseExpression();
        }
        matchToken(TokenType.SEMI_TOKEN);
        return new ExpressionStatement(expr);
    }
    
    private SelectionStatement parseSelectionStmt() {
        matchToken(TokenType.IF_TOKEN);
        matchToken(TokenType.LPAREN_TOKEN);
        Expression expr = parseExpression();
        matchToken(TokenType.RPAREN_TOKEN);
        Statement ifStmt = parseStatement();
        Statement elseStmt = null;
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.ELSE_TOKEN) {
            matchToken(TokenType.ELSE_TOKEN);
            elseStmt = parseStatement();
        }
        return new SelectionStatement(expr, ifStmt, elseStmt);
    }
    
    private IterationStatement parseIterationStmt() {
        matchToken(TokenType.WHILE_TOKEN);
        matchToken(TokenType.LPAREN_TOKEN);
        Expression expr = parseExpression();
        matchToken(TokenType.RPAREN_TOKEN);
        Statement stmt = parseStatement();
        return new IterationStatement(expr, stmt);
    }
    
    private ReturnStatement parseReturnStmt() {
        matchToken(TokenType.RET_TOKEN);
        Expression expr = null;
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.NUM_TOKEN ||
            type == TokenType.LPAREN_TOKEN ||
            type == TokenType.ID_TOKEN) {
            expr = parseExpression();
        }
        matchToken(TokenType.SEMI_TOKEN);
        return new ReturnStatement(expr);
    }
    
    
    
    
    
    /**
     * This is a helper method to help match tokens
     * @param type 
     */
    private void matchToken(TokenType type) {
        if (scan.viewNextToken().getTokenType() == type) {
            scan.getNextToken();
        } else {
            throw new ParseError("matchToken error: expected " + type + 
                    " and found " + scan.viewNextToken().getTokenType());
        }
    }
    
    /**
     * This is a helper method to match an ID token and get the ID string
     * @return 
     */
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
    
    /**
     * This is a helper method to match a NUM token and get the int value
     * @return 
     */
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
