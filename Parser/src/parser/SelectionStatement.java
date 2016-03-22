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
            
            if (ifStatement instanceof CompoundStatement) {
                ((CompoundStatement) ifStatement).printMe(out, indent + 1);
            } else if (ifStatement instanceof ExpressionStatement) {
                ((ExpressionStatement) ifStatement).printMe(out, indent + 1);
            } else if (ifStatement instanceof IterationStatement) {
                ((IterationStatement) ifStatement).printMe(out, indent + 1);
            } else if (ifStatement instanceof ReturnStatement) {
                ((ReturnStatement) ifStatement).printMe(out, indent + 1);
            } else if (ifStatement instanceof SelectionStatement) {
                ((SelectionStatement) ifStatement).printMe(out, indent + 1);
            }
            out.write('\n');
            
            if (elseStatement == null) {
                
            } else if (elseStatement instanceof CompoundStatement) {
                ((CompoundStatement) elseStatement).printMe(out, indent + 1);
            } else if (elseStatement instanceof ExpressionStatement) {
                ((ExpressionStatement) elseStatement).printMe(out, indent + 1);
            } else if (elseStatement instanceof IterationStatement) {
                ((IterationStatement) elseStatement).printMe(out, indent + 1);
            } else if (elseStatement instanceof ReturnStatement) {
                ((ReturnStatement) elseStatement).printMe(out, indent + 1);
            } else if (elseStatement instanceof SelectionStatement) {
                ((SelectionStatement) elseStatement).printMe(out, indent + 1);
            }
            out.write('\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in CompoundStatement");
        }
    } 
}
