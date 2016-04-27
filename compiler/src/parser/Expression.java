package parser;

import java.io.FileWriter;
import lowlevel.BasicBlock;
import lowlevel.Function;

/**
 * This acts as a parent class for:
 *      AssignExpression
 *      BinaryExpression
 *      IdExpression
 *      NumExpression
 *      VarCallExpression
 * @author Ryan
 */
public abstract class Expression {
    /**
     * Helper function for printMe on Expressions
     * @param out
     * @param indent
     * @param expr 
     */
    public void printMe(FileWriter out, int indent, Expression expr) {
        if (expr instanceof AssignExpression) {
            ((AssignExpression) expr).printMe(out, indent);
        } else if (expr instanceof BinaryExpression) {
            ((BinaryExpression) expr).printMe(out, indent);
        } else if (expr instanceof CallExpression) {
            ((CallExpression) expr).printMe(out, indent);
        } else if (expr instanceof NumExpression) {
            ((NumExpression) expr).printMe(out, indent);
        } else if (expr instanceof VarExpression) {
            ((VarExpression) expr).printMe(out, indent);
        }
    }
    
    /**
     * Abstract method used for all expression classes
     * @param func 
     */
    public abstract void genLLCode(Function func);
}
