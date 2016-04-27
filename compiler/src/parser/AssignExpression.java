package parser;

import java.io.FileWriter;
import java.io.IOException;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

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
            
            expression.printMe(out, indent+1, expression);
        }
        catch (IOException e) {
            System.out.println("Error writing to file in AssignExpression");
        } 
    }
    
    @Override
    public void genLLCode(Function func) {
        // Simply checks if var is contained in symbol table
        var.genLLCode(func);
        expression.genLLCode(func);
        Integer varRegNum = (Integer) func.getTable().get(var.getIdentifier());
        Integer exprRegNum = expression.getRegisterNum();
        Operation oper = new Operation(Operation.OperationType.ASSIGN, func.getCurrBlock());
        Operand lhs = new Operand(Operand.OperandType.REGISTER, varRegNum);
        Operand rhs = new Operand(Operand.OperandType.REGISTER, exprRegNum);
        oper.setSrcOperand(0, rhs);
        oper.setDestOperand(0, lhs);
        func.getCurrBlock().appendOper(oper);
    }
}
