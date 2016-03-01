package parser;

/**
 *
 * @author Ryan
 */
public class BinaryExpression extends Expression {
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
    
    private Expression lhs;
    private Expression rhs;
    OpType  op;
}
