package parser;

import java.io.FileWriter;
import java.io.IOException;

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
     * @param out
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("ReturnStatement" + '\n');

            if (expression != null) {
                expression.printMe(out, indent+1, expression);
            } else {
                out.write("No return expression");
            }
            out.write('\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in ReturnStatement");
        }
    }
}
