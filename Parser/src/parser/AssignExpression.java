package parser;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class defines an Assign Expression in the C Minus language
 * It is of the form var = expression;
 * @author Ryan
 */
public class AssignExpression extends Expression {
    /**
     * This holds the var that will take the expression value
     */
    private VarExpression var;
    
    /**
     * This holds the expression that will be assigned into the var
     */
    private Expression expression;
    
    /**
     * Construction
     * @param v
     * @param expr 
     */
    public AssignExpression(VarExpression v, Expression expr) {
        var        = v;
        expression = expr;
    }
    
    /**
     * This method prints the attributes of AssignExpression
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("AssignExpression\n");
            
            var.printMe(out, indent+1);
            
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
