package parser;

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
}
