package parser;

import java.io.FileWriter;
import java.io.IOException;
import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/**
 * This class defines an iteration-stmt of the C Minus language
 * It is a while loop
 * @author Ryan
 */
public class IterationStatement extends Statement {
    
    /**
     * This variable holds the condition for the while loop
     */
    private Expression expression;
    
    /**
     * This holds the definition of the while loop
     */
    private Statement  statement;
    
    /**
     * Constructor
     * @param expr
     * @param stmt 
     */
    public IterationStatement(Expression expr, Statement stmt) {
        expression = expr;
        statement  = stmt;
    }
    
    /**
     * This method will print the attributes of IterationStatement
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("IterationStatement" + '\n');

            expression.printMe(out, indent+1, expression);
            
            statement.printMe(out, indent+1, statement);
        }
        catch (IOException e) {
            System.out.println("Error writing to file in IterationStatement");
        }
    }
    
    public void genLLCode(Function func) {
        //Create blocks needed
        BasicBlock condBlock = func.getCurrBlock();
        BasicBlock bodyBlock = new BasicBlock(func);
        BasicBlock postBlock = new BasicBlock(func);
        
        //Gen branch operation
        expression.genLLCode(func);
        Operation branch = new Operation(Operation.OperationType.BEQ, condBlock);
        Operand src1 = new Operand(Operand.OperandType.REGISTER, expression.getRegisterNum());
        Operand src2 = new Operand(Operand.OperandType.INTEGER, 0);
        branch.setSrcOperand(0, src1);
        branch.setSrcOperand(1, src2);
        branch.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum()));
        func.getCurrBlock().appendOper(branch);
        
        //Append body block
        func.appendToCurrentBlock(bodyBlock);
        
        //Set current block to body block
        func.setCurrBlock(bodyBlock);
        
        //genLLCode body block
        statement.genLLCode(func);
        
        //Reevaluate condition
        expression.genLLCode(func);
        
        //Add jump operation if condition is still true
        branch = new Operation(Operation.OperationType.BNE, bodyBlock);
        src1 = new Operand(Operand.OperandType.REGISTER, expression.getRegisterNum());
        branch.setSrcOperand(0, src1);
        branch.setSrcOperand(1, src2);
        branch.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, bodyBlock.getBlockNum()));
        func.getCurrBlock().appendOper(branch);
        
        //Append post block
        func.appendToCurrentBlock(postBlock);
        
        //Set current block to post block
        func.setCurrBlock(postBlock);
    }
}
