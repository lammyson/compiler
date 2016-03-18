package parser;

/**
 * This class defines a return-stmt in the C Minus language
 * @author Ryan
 */
public class ReturnStatement extends Statement {
    
    /**
     * This variable holds an optional expression in the return statement
     */
    private Expression expression;
    
    /**
     * Constructor
     * @param expr 
     */
    public ReturnStatement(Expression expr) {
        expression = expr;
    }
    
    /**
     * This method will print the attributes of ReturnStatement
     */
    private void printMe() {
        
    }
}
