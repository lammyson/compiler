package parser;

import java.io.FileWriter;
import java.io.IOException;

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
            
            if (statement instanceof CompoundStatement) {
                ((CompoundStatement) statement).printMe(out, indent + 1);
            } else if (statement instanceof ExpressionStatement) {
                ((ExpressionStatement) statement).printMe(out, indent + 1);
            } else if (statement instanceof IterationStatement) {
                ((IterationStatement) statement).printMe(out, indent + 1);
            } else if (statement instanceof ReturnStatement) {
                ((ReturnStatement) statement).printMe(out, indent + 1);
            } else if (statement instanceof SelectionStatement) {
                ((SelectionStatement) statement).printMe(out, indent + 1);
            }
            out.write('\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in CompoundStatement");
        }
    }
}
