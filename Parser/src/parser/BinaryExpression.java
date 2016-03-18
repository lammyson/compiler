package parser;

/**
 * This defines a binary expression which has a left hand side, right hand
 * side, and operation on the 2 sides. The valid operations are addops,
 * mulops, and relops
 * @author Ryan
 */
public class BinaryExpression extends Expression {
    
    /**
     * This enum holds the types of operations for a BinaryExpression
     */
    public enum OpType {
        PLUS,
        MINUS,
        MULT,
        DIV,
        LESS_THAN_EQ,
        LESS_THAN,
        GREATER_THAN,
        GREATER_THAN_EQ,
        EQUAL,
        NOT_EQUAL
    }
    
    /**
     * This variable holds the left hand side of the BinaryExpression
     */
    private Expression lhs;
    
    /**
     * This variable holds the operations that the lhs and rhs act on
     */
    private OpType     operation;
    
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
    public BinaryExpression(Expression left, OpType op, Expression right) {
        lhs       = left;
        operation = op;
        rhs       = right;
    }
    
    /**
     * This method will print the attributes of BinaryExpression
     */
    private void printMe() {
        
    }
}