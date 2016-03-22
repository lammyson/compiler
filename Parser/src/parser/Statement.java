package parser;

import java.io.FileWriter;

/**
 * This acts as a parent class for:
 *      CompoundStatement
 *      ExpressionStatement
 *      IterationStatement
 *      ReturnStatement
 *      SelectionStatement
 * @author Ryan
 */
public abstract class Statement {
    /**
     * Helper function for printMe on Statements
     * @param out
     * @param indent
     * @param statement 
     */
    public void printMe(FileWriter out, int indent, Statement statement) {
        if (statement instanceof CompoundStatement) {
            ((CompoundStatement) statement).printMe(out, indent);
        } else if (statement instanceof ExpressionStatement) {
            ((ExpressionStatement) statement).printMe(out, indent);
        } else if (statement instanceof IterationStatement) {
            ((IterationStatement) statement).printMe(out, indent);
        } else if (statement instanceof ReturnStatement) {
            ((ReturnStatement) statement).printMe(out, indent);
        } else if (statement instanceof SelectionStatement) {
            ((SelectionStatement) statement).printMe(out, indent);
        }
    }
}
