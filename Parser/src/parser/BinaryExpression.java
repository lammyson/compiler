package parser;

import java.io.FileWriter;
import java.io.IOException;
import scanner.Token.TokenType;

/**
 * This defines a binary expression which has a left hand side, right hand
 * side, and operation on the 2 sides. The valid operations are addops,
 * mulops, and relops
 * @author Ryan
 */
public class BinaryExpression extends Expression {
    /**
     * This variable holds the left hand side of the BinaryExpression
     */
    private Expression lhs;
    
    /**
     * This variable holds the operations that the lhs and rhs act on
     */
    private TokenType  operation;
    
    /**
     * This variable holds the right hand side of the BinaryExpression
     */
    private Expression rhs;
    
    /**
     * Constructor
     * @param left
     * @param op
     * @param right 
     */
    public BinaryExpression(Expression left, TokenType op, Expression right) {
        lhs       = left;
        operation = op;
        rhs       = right;
    }
    
    /**
     * This method will print the attributes of BinaryExpression
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("BinaryExpression " + operation.toString() + '\n');
            
            lhs.printMe(out, indent+1, lhs);
            out.write('\n');
            
            rhs.printMe(out, indent+1, rhs);
            out.write('\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in BinaryExpression");
        }
    }
}