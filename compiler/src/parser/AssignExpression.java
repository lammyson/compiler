package parser;

import compiler.CMinusCompiler;
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
        expression.genLLCode(func);
        Integer varRegNum;
        boolean isGlobal = false;
        if (func.getTable().containsKey(var.getIdentifier())) {
            varRegNum = func.getTable().get(var.getIdentifier());            
        } else {
            // Store value in new register to be stored later
            varRegNum = func.getNewRegNum();
        }
        if (CMinusCompiler.globalHash.containsKey(var.getIdentifier())) {
            isGlobal = true;
        }
        this.setRegisterNum(varRegNum);
        Integer exprRegNum = expression.getRegisterNum();
        if (isGlobal) {
            Operation storeOper = new Operation(Operation.OperationType.STORE_I, func.getCurrBlock());
            Operand storeReg = new Operand(Operand.OperandType.REGISTER, exprRegNum);
            storeOper.setSrcOperand(0, storeReg);
            Operand storeString = new Operand(Operand.OperandType.STRING, var.getIdentifier());
            storeOper.setSrcOperand(1, storeString);
            Operand offset = new Operand(Operand.OperandType.INTEGER, 0);
            storeOper.setSrcOperand(2, offset);
            func.getCurrBlock().appendOper(storeOper);
        } else {
            Operation assignOper = new Operation(Operation.OperationType.ASSIGN, func.getCurrBlock());
            Operand lhs = new Operand(Operand.OperandType.REGISTER, varRegNum);
            assignOper.setDestOperand(0, lhs);
            Operand rhs = new Operand(Operand.OperandType.REGISTER, exprRegNum);
            assignOper.setSrcOperand(0, rhs);
            func.getCurrBlock().appendOper(assignOper);
        }
    }
}
