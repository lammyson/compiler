package parser;

import scanner.Token;
import scanner.CMinusScanner;

/**
 *
 * @author Ryan
 */
public abstract class Expression {
    public Expression() {
        
    }
    
    public Expression parseExpression() {
        return new BinaryExpression();
    }
    
    private Expression parseFactor () {
        switch (viewNextToken()) {
            case Token.TokenType.LPAREN_TOKEN:
                advanceToken();
                Expression returnExpr = parseExpression ();
                matchToken(Token.TokenType.RPAREN_TOKEN);
                return returnExpr;
            case Token.TokenType.ID_TOKEN:
                Token oldToken = advanceToken();
                return IdExpression(oldToken);
            case Token.TokenType.NUM_TOKEN:
               Token oldToken = advanceToken();
                return NumExpression(oldToken);
            default:
                //logParseError();
                return null;
        }
    }

}
