package parser;

import java.io.FileWriter;
import java.io.IOException;
import lowlevel.Function;

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
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("IterationStatement" + '\n');

            expression.printMe(out, indent+1, expression);
            
            statement.printMe(out, indent+1, statement);
        }
        catch (IOException e) {
            System.out.println("Error writing to file in IterationStatement");
        }
    }
    
    public void genLLCode(Function func) {
        
    }
}
