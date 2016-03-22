package parser;

import java.io.FileWriter;

/**
 * This class encapsulates variables in the C Minus language
 * @author Ryan
 */
public class VarExpression extends Expression {
    
    /**
     * This variable holds the ID of the var 
     */
    private String     identifier;
    
    /**
     * This variable holds an expression for an array var. Can be null.
     */
    private Expression expression;
    
    /**
     * Constructor
     * @param id
     * @param expr
     */
    public VarExpression(String id, Expression expr) {
        identifier = id;
        expression = expr;
    }
    
    /**
     * This method will print the attributes of VarExpression
     */
    public void printMe(FileWriter out) {
        
    }
}
