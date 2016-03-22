package parser;

import java.io.FileWriter;

/**
 * This class defines an expression-stmt in the C Minus language
 * @author Ryan
 */
public class ExpressionStatement extends Statement {
    
    /**
     * This variable holds an optional expression. Can be null
     */
    private Expression expression;
    
    /**
     * Constructor
     * @param expr 
     */
    public ExpressionStatement(Expression expr) {
        expression = expr;
    }
    
    /**
     * This method prints the attributes of ExpressionStatement
     */
    public void printMe(FileWriter out) {
        
    }
}
