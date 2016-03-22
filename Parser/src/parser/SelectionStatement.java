package parser;

import java.io.FileWriter;

/**
 * This class defines a selection-stmt in the C Minus language
 * It is an if statement with an optional else statement
 * @author Ryan
 */
public class SelectionStatement extends Statement {
       
    /**
     * This variable holds the condition of the if statement
     */
    private Expression expression;
    
    /**
     * This variable holds the statement attached to the if portion
     */
    private Statement  ifStatement;
    
    /**
     * This variable holds the statement attached to the else portion
     */
    private Statement  elseStatement;
    
    /**
     * Constructor
     * @param expr 
     */
    public SelectionStatement(Expression expr, Statement ifstmt, Statement elsestmt) {
        expression    = expr;
        ifStatement   = ifstmt;
        elseStatement = elsestmt;
    }
    
    /**
     * This method will print the attributes of SelectionStatement
     */
    public void printMe(FileWriter out) {
        
    } 
}
