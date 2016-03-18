package parser;

/**
 * This class defines an iteration-stmt of the C Minus language
 * It is a while loop
 * @author Ryan
 */
public class IterationStatement extends Statement {
    
    /**
     * This variable holds the condition for the while loop
     */
    private Expression expression;
    
    /**
     * This holds the definition of the while loop
     */
    private Statement  statement;
    
    /**
     * Constructor
     * @param expr
     * @param stmt 
     */
    public IterationStatement(Expression expr, Statement stmt) {
        expression = expr;
        statement  = stmt;
    }
    
    /**
     * This method will print the attributes of IterationStatement
     */
    private void printMe() {
        
    }
}
