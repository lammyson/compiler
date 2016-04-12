package parser;

import java.io.FileWriter;
import java.io.IOException;

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
     * This variable holds the statement attached to the else portion. Can be null
     */
    private Statement  elseStatement;
    
    /**
     * Constructor
     * @param expr 
     * @param ifstmt 
     * @param elsestmt 
     */
    public SelectionStatement(Expression expr, Statement ifstmt, Statement elsestmt) {
        expression    = expr;
        ifStatement   = ifstmt;
        elseStatement = elsestmt;
    }
    
    /**
     * This method will print the attributes of SelectionStatement
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("SelectionStatement" + '\n');

            expression.printMe(out, indent+1, expression);
            
            ifStatement.printMe(out, indent+1, ifStatement);
            
            if (elseStatement != null) {
                elseStatement.printMe(out, indent+1, elseStatement);
            } 
        }
        catch (IOException e) {
            System.out.println("Error writing to file in SelectionStatement");
        }
    } 
}
