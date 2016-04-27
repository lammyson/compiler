package parser;

import java.io.FileWriter;
import java.io.IOException;
import lowlevel.Function;

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
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("ExpressionStatement" + '\n');
            if (expression != null) {
                expression.printMe(out, indent+1, expression);
            }
            
        }
        catch (IOException e) {
            System.out.println("Error writing to file in ExpressionStatement");
        }
    }
    
    /**
     * This method generates low level code for expression statements
     * @param func 
     */
    public void genLLCode(Function func) {
        expression.genLLCode(func);
    }
}
