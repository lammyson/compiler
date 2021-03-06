package parser;

import java.io.FileWriter;
import java.io.IOException;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;

/**
 * This class defines a return-stmt in the C Minus language
 * @author Ryan
 */
public class ReturnStatement extends Statement {
    
    /**
     * This variable holds an optional expression in the return statement
     */
    private Expression expression;
    
    /**
     * Constructor
     * @param expr 
     */
    public ReturnStatement(Expression expr) {
        expression = expr;
    }
    
    /**
     * This method will print the attributes of ReturnStatement
     * @param out
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("ReturnStatement" + '\n');

            if (expression != null) {
                expression.printMe(out, indent+1, expression);
            }
        }
        catch (IOException e) {
            System.out.println("Error writing to file in ReturnStatement");
        }
    }
    
    /**
     * This method makes low level code for return statements
     * @param func 
     */
    public void genLLCode(Function func) {
        BasicBlock currBlock = func.getCurrBlock();
        BasicBlock returnBlock = func.getReturnBlock();
        if (expression != null) {
            expression.genLLCode(func);
            Operation setReturn = new Operation(Operation.OperationType.ASSIGN, currBlock);
            Operand retSrc = new Operand(Operand.OperandType.REGISTER, expression.getRegisterNum());
            Operand retDest = new Operand(Operand.OperandType.MACRO, "RetReg");
            setReturn.setSrcOperand(0, retSrc);
            setReturn.setDestOperand(0, retDest);
            currBlock.appendOper(setReturn);
        }
        Operation newOper = new Operation(OperationType.JMP, currBlock);
        Operand src = new Operand(Operand.OperandType.BLOCK, returnBlock.getBlockNum());
        newOper.setSrcOperand(0, src);
        currBlock.appendOper(newOper);
    }
}
