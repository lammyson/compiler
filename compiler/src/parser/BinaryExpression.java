package parser;

import java.io.FileWriter;
import java.io.IOException;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
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
            
            rhs.printMe(out, indent+1, rhs);
        }
        catch (IOException e) {
            System.out.println("Error writing to file in BinaryExpression");
        }
    }
    
    /**
     * This method generates low level code for binary expressions
     * @param func 
     */
    @Override
    public void genLLCode(Function func) {
        lhs.genLLCode(func);
        Integer lhsRegNum = lhs.getRegisterNum();
        
        rhs.genLLCode(func);
        Integer rhsRegNum = rhs.getRegisterNum();
        
        Integer newRegNum = func.getNewRegNum();
        OperationType operType = null;
        switch(operation) {
            case ADD_TOKEN:
                operType = OperationType.ADD_I;
                break;
            case SUB_TOKEN:
                operType = OperationType.SUB_I;
                break;
            case MUL_TOKEN:
                operType = OperationType.MUL_I;
                break;
            case DIV_TOKEN:
                operType = OperationType.DIV_I;
                break;
            case LESS_TOKEN:
                operType = OperationType.LT;
                break;
            case LESS_EQ_TOKEN:
                operType = OperationType.LTE;
                break;
            case GREAT_TOKEN:
                operType = OperationType.GT;
                break;
            case GREAT_EQ_TOKEN:
                operType = OperationType.GTE;
                break;
            case EQUAL_TOKEN:
                operType = OperationType.EQUAL;
                break;
            case NOT_EQ_TOKEN:
                operType = OperationType.NOT_EQUAL;
                break;
            default:
                throw new CodeGenerationException("Operation type not found");
        }
        Operation newOper = new Operation(operType, func.getCurrBlock());
        Operand op1 = new Operand(OperandType.REGISTER, lhsRegNum);
        Operand op2 = new Operand(OperandType.REGISTER, rhsRegNum);
        Operand op3 = new Operand(OperandType.REGISTER, newRegNum);
        newOper.setSrcOperand(0, op1);
        newOper.setSrcOperand(1, op2);
        newOper.setDestOperand(0, op3);
        this.setRegisterNum(newRegNum);
        func.getCurrBlock().appendOper(newOper);
    }
}