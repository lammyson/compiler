package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import scanner.CMinusScanner;
import scanner.Token;
import scanner.Token.TokenType;

/**
 * This class will implement a C Minus Parser
 * @author Ryan
 */
public class CMinusParser implements Parser {
    /**
     * This holds the C Minus Scanner
     */
    CMinusScanner scan;
    Program program;
    
    /**
     * Constructor
     * @param s 
     */
    public CMinusParser(CMinusScanner s) {
        scan = s;
    }
    
    public void printTree(FileWriter out) {
        program.printMe(out);
    }
    
    public void parse() {
        program = parseProgram();
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
            return new VarDeclaration(id, -1);
        } else if (type == TokenType.LSBRACK_TOKEN) {
            matchToken(TokenType.LSBRACK_TOKEN);
            int num = matchNumToken();
            matchToken(TokenType.RSBRACK_TOKEN);
            matchToken(TokenType.SEMI_TOKEN);
            return new VarDeclaration(id, num);
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
        return new FunDeclaration(returnType, id,
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
            return new VarDeclaration(id, num);
        } else {
            matchToken(TokenType.SEMI_TOKEN);
            return new VarDeclaration(id, -1);
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
            return new Param(id, true);
        } else if (type == TokenType.COMMA_TOKEN || 
                   type == TokenType.RPAREN_TOKEN) {
            return new Param(id, false);
        } else {
            throw new ParseError("Error in parseParam: unexpected token " 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    /**
     * This method parses a compound-stmt
     * @return 
     */
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
    
    /**
     * This method parses a statement
     * @return 
     */
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
    
    /**
     * This method parses an expression-stmt
     * @return 
     */
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
    
    /**
     * This method parses a selection-stmt
     * @return 
     */
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
    
    /**
     * This method parses an iteration-stmt
     * @return 
     */
    private IterationStatement parseIterationStmt() {
        matchToken(TokenType.WHILE_TOKEN);
        matchToken(TokenType.LPAREN_TOKEN);
        Expression expr = parseExpression();
        matchToken(TokenType.RPAREN_TOKEN);
        Statement stmt = parseStatement();
        return new IterationStatement(expr, stmt);
    }
    
    /**
     * This method parses a return-stmt
     * @return 
     */
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
     * This method parses an expression
     * @return 
     */
    private Expression parseExpression() {
        TokenType type = scan.viewNextToken().getTokenType();
        Expression expr = null;
        if (type == TokenType.NUM_TOKEN) {
            int num = matchNumToken();
            expr = parseSimpleExpression(new NumExpression(num));
        } else if (type == TokenType.LPAREN_TOKEN) {
            matchToken(TokenType.LPAREN_TOKEN);
            Expression expr1 = parseExpression();
            matchToken(TokenType.RPAREN_TOKEN);
            expr = parseSimpleExpression(expr1);
        } else if (type == TokenType.ID_TOKEN) {
            String id = matchIDToken();
            expr = parseExpression1(id);
        } else {
            throw new ParseError("Error in parseExpression: unexpected token " 
                    + scan.viewNextToken().getTokenType());
        }
        return expr;
    }
    
    /**
     * This method parses an expression prime
     * @param id used to make var, array var, call, or simple-expression
     * @return 
     */
    private Expression parseExpression1(String id) {
        TokenType type = scan.viewNextToken().getTokenType();
        Expression expr = null;
        if (type == TokenType.ASSIGN_TOKEN) {
            VarExpression lhs = new VarExpression(id, null);
            matchToken(TokenType.ASSIGN_TOKEN);
            Expression rhs = parseExpression();
            expr = new AssignExpression(lhs, rhs);
        } else if (type == TokenType.LSBRACK_TOKEN) {
            matchToken(TokenType.LSBRACK_TOKEN);
            Expression expr1 = parseExpression();
            VarExpression arrayExpr = new VarExpression(id, expr1);
            matchToken(TokenType.RSBRACK_TOKEN);
            expr = parseExpression2(arrayExpr);
        } else if (type == TokenType.LPAREN_TOKEN) {
            matchToken(TokenType.LPAREN_TOKEN);
            ArrayList<Expression> args = parseArgs();
            matchToken(TokenType.RPAREN_TOKEN);
            CallExpression call = new CallExpression(id, args);
            expr = parseSimpleExpression(call);
        } else if (checkFactorFollowSet(type)) {
            expr = parseSimpleExpression(new VarExpression(id, null));
        }
        return expr;
    }
    
    /**
     * Thie method parses an expression double prime
     * @param var
     * @return 
     */
    private Expression parseExpression2(VarExpression var) {
        TokenType type = scan.viewNextToken().getTokenType();
        Expression expr = null;
        if (type == TokenType.ASSIGN_TOKEN) {
            matchToken(TokenType.ASSIGN_TOKEN);
            Expression rhs = parseExpression();
            expr = new AssignExpression(var, rhs);
        } else if (checkFactorFollowSet(type)) {
            expr = parseSimpleExpression(var);
        }
        return expr;
    }
    
    /**
     * This method parses a simple-expression
     * @param lhs of a possible BinaryExpression
     * @return 
     */
    private Expression parseSimpleExpression(Expression expr) {
        Expression lhs = parseAdditiveExpression(expr); //Can be null
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.LESS_TOKEN     ||
                type == TokenType.LESS_EQ_TOKEN  ||
                type == TokenType.GREAT_TOKEN    ||
                type == TokenType.GREAT_EQ_TOKEN ||
                type == TokenType.EQUAL_TOKEN    ||
                type == TokenType.NOT_EQ_TOKEN) {
            scan.getNextToken();
            Expression rhs = parseAdditiveExpression(null);
            BinaryExpression binExpr = new BinaryExpression(lhs, type, rhs);
            return binExpr;
        } else if (type == TokenType.SEMI_TOKEN ||
                type == TokenType.RPAREN_TOKEN  ||
                type == TokenType.RSBRACK_TOKEN ||
                type == TokenType.COMMA_TOKEN) {
            return lhs;
        } else {
            throw new ParseError("Error in parseSimpleExpression: unexpected token " 
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    //parseAdditiveExpression
    private Expression parseAdditiveExpression(Expression expr) {
        Expression newExpr;
        Expression lhs = parseTerm(expr);
        TokenType type = scan.viewNextToken().getTokenType();
        while (type == TokenType.ADD_TOKEN || type == TokenType.SUB_TOKEN) {
            scan.getNextToken();
            Expression rhs = parseTerm(null);
            newExpr = new BinaryExpression(lhs, type, rhs);
            lhs = newExpr;
            type = scan.viewNextToken().getTokenType();
        }
        return lhs;    
    }
    
    //parseTerm
    private Expression parseTerm(Expression expr) {
        Expression newExpr;
        Expression lhs;
        if (expr == null) {
            lhs = parseFactor();
        } else {
            lhs = expr;
        }
        TokenType type = scan.viewNextToken().getTokenType();
        while (type == TokenType.MUL_TOKEN || type == TokenType.DIV_TOKEN) {
            scan.getNextToken();
            Expression rhs = parseFactor();
            newExpr = new BinaryExpression(lhs, type, rhs);
            lhs = newExpr;
            type = scan.viewNextToken().getTokenType();
        }
        return lhs;  
    }
    
    //parseFactor
    private Expression parseFactor() {
        TokenType type = scan.viewNextToken().getTokenType();
        Expression expr = null;
        if (type == TokenType.LPAREN_TOKEN) {
            matchToken(TokenType.LPAREN_TOKEN);
            expr = parseExpression();
            matchToken(TokenType.RPAREN_TOKEN);
        } else if (type == TokenType.NUM_TOKEN) {
            int num = matchNumToken();
            expr = new NumExpression(num);
        } else if (type == TokenType.ID_TOKEN) {
            String id = matchIDToken();
            expr = parseVarCall(id);
        }
        return expr;
    }
    
    /**
     * This method parses a varcall
     * @param id needed to create the IdExpression for var, array var, or call
     * @return 
     */
    private Expression parseVarCall(String id) {
        TokenType type = scan.viewNextToken().getTokenType();
        Expression expr;
        if (type == TokenType.LPAREN_TOKEN) {
            matchToken(TokenType.LPAREN_TOKEN);
            ArrayList<Expression> argList = parseArgs();
            matchToken(TokenType.RPAREN_TOKEN);
            expr = new CallExpression(id, argList);
        } else if (type == TokenType.LSBRACK_TOKEN) {
            matchToken(TokenType.LSBRACK_TOKEN);
            Expression arrayExpr = parseExpression();
            matchToken(TokenType.RSBRACK_TOKEN);
            expr = new VarExpression(id, arrayExpr);
        } else if (checkFactorFollowSet(type)) {
            expr = new VarExpression(id, null);
        } else {
            throw new ParseError("Error in parseVarCall: unexpected token "
                    + scan.viewNextToken().getTokenType());
        }
        return expr;
    }
    
    /**
     * This method parses an args
     * @return 
     */
    private ArrayList<Expression> parseArgs() {
        TokenType type = scan.viewNextToken().getTokenType();
        if (type == TokenType.INT_TOKEN || type == TokenType.NUM_TOKEN) {
            return parseArgsList();
        } else if (type == TokenType.RPAREN_TOKEN) {
            return null;
        } else {
            throw new ParseError("Error in parseArgs: unexpected token "
                    + scan.viewNextToken().getTokenType());
        }
    }
    
    /**
     * This method parses an args-list
     * @return 
     */
    private ArrayList<Expression> parseArgsList() {
        ArrayList<Expression> argList = new ArrayList<Expression>();
        argList.add(parseExpression());
        while (scan.viewNextToken().getTokenType() == TokenType.COMMA_TOKEN) {
            matchToken(TokenType.COMMA_TOKEN);
            TokenType type = scan.viewNextToken().getTokenType();
            if (type == TokenType.NUM_TOKEN ||
                type == TokenType.LPAREN_TOKEN ||
                type == TokenType.ID_TOKEN) {
                argList.add(parseExpression());
            } else {
                throw new ParseError("Error in parseArgsList: unexpected token "
                    + scan.viewNextToken().getTokenType());
            }
        }
        return argList;
    }
    
    /**
     * Thie method takes type and checks if it is in the follow set of factor
     * @param type
     * @return 
     */
    private boolean checkFactorFollowSet(TokenType type) {
        return (type == TokenType.MUL_TOKEN ||
            type == TokenType.DIV_TOKEN ||
            type == TokenType.ADD_TOKEN ||
            type == TokenType.SUB_TOKEN ||
            type == TokenType.LESS_EQ_TOKEN ||
            type == TokenType.LESS_TOKEN ||
            type == TokenType.GREAT_TOKEN ||
            type == TokenType.GREAT_EQ_TOKEN ||
            type == TokenType.EQUAL_TOKEN ||
            type == TokenType.NOT_EQ_TOKEN ||
            type == TokenType.SEMI_TOKEN ||
            type == TokenType.RPAREN_TOKEN ||
            type == TokenType.RSBRACK_TOKEN ||
            type == TokenType.COMMA_TOKEN);
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
        try {
            // Initialize input file and scanner
            File filename = new File(args[0]);
            FileReader fReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fReader);
            CMinusScanner cScan = new CMinusScanner(reader);
            CMinusParser cParse = new CMinusParser(cScan);
            cParse.parse();
            FileWriter writer = new FileWriter(args[1], true);
            cParse.printTree(writer);
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
        catch (IOException ioe) {
            System.out.println("Problem writing to file");
        }
    }   
}