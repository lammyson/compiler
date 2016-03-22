package parser;

import java.io.FileWriter;
import java.io.IOException;

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

            if (expression instanceof AssignExpression) {
                ((AssignExpression) expression).printMe(out, indent + 1);
            } else if (expression instanceof BinaryExpression) {
                ((BinaryExpression) expression).printMe(out, indent + 1);
            } else if (expression instanceof CallExpression) {
                ((CallExpression) expression).printMe(out, indent + 1);
            } else if (expression instanceof NumExpression) {
                ((NumExpression) expression).printMe(out, indent + 1);
            } else if (expression instanceof VarExpression) {
                ((VarExpression) expression).printMe(out, indent + 1);
            }
            out.write('\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in CompoundStatement");
        }
    }
}
