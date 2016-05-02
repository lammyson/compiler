package parser;

import java.io.FileWriter;
import java.io.IOException;
import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/**
 * This class defines a selection-stmt in the C Minus language
 * It is an if statement with an optional else statement
 * @author Ryan
 */
public class SelectionStatement extends Statement {
       
    /**
     * This variable holds the condition of the if statement
     */
    private Expression expression;
    
    /**
     * This variable holds the statement attached to the if portion
     */
    private Statement  ifStatement;
    
    /**
     * This variable holds the statement attached to the else portion. Can be null
     */
    private Statement  elseStatement;
    
    /**
     * Constructor
     * @param expr 
     * @param ifstmt 
     * @param elsestmt 
     */
    public SelectionStatement(Expression expr, Statement ifstmt, Statement elsestmt) {
        expression    = expr;
        ifStatement   = ifstmt;
        elseStatement = elsestmt;
    }
    
    /**
     * This method will print the attributes of SelectionStatement
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("SelectionStatement" + '\n');

            expression.printMe(out, indent+1, expression);
            
            ifStatement.printMe(out, indent+1, ifStatement);
            
            if (elseStatement != null) {
                elseStatement.printMe(out, indent+1, elseStatement);
            } 
        }
        catch (IOException e) {
            System.out.println("Error writing to file in SelectionStatement");
        }
    } 
    
    public void genLLCode(Function func) {
        BasicBlock ifBlock = func.getCurrBlock();
        BasicBlock thenBlock = new BasicBlock(func);
        BasicBlock postBlock = new BasicBlock(func);
        BasicBlock elseBlock = null;

        //genLLCode branch
        expression.genLLCode(func);
        Operation branch = new Operation(Operation.OperationType.BEQ, ifBlock);
        Operand src1 = new Operand(Operand.OperandType.REGISTER, expression.getRegisterNum());
        Operand src2 = new Operand(Operand.OperandType.INTEGER, 0);
        branch.setSrcOperand(0, src1);
        branch.setSrcOperand(1, src2);
        
        //Do this if else part exists
        if (elseStatement != null) {
            elseBlock = new BasicBlock(func);
            branch.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, elseBlock.getBlockNum()));
        } else {
            branch.setDestOperand(0, new Operand(Operand.OperandType.BLOCK, postBlock.getBlockNum()));
        }
        func.getCurrBlock().appendOper(branch);

        //Append then
        func.appendToCurrentBlock(thenBlock);
        
        //Set current block to then block
        func.setCurrBlock(thenBlock);
        
        //genLLCode then block
        ifStatement.genLLCode(func);
        
        //Append post block
        func.appendToCurrentBlock(postBlock);
        
        //Do this if else part exists
        if (elseStatement != null) {
            //Set current block to else
            func.setCurrBlock(elseBlock);
            
            //genLLCode else block
            elseStatement.genLLCode(func);
            
            //Add jump to else block if else doesn't end in return
            Operation lastOper = func.getCurrBlock().getLastOper();
            if (lastOper.getType() != Operation.OperationType.RETURN && 
                    lastOper.getType() != Operation.OperationType.JMP) {
                
                Operation jmpOper = new Operation(Operation.OperationType.JMP, func.getCurrBlock());
                Operand src = new Operand(Operand.OperandType.BLOCK, elseBlock.getBlockNum());
                jmpOper.setSrcOperand(0, src);
                func.getCurrBlock().appendOper(jmpOper);
            }
            
            //Append else block
            func.appendUnconnectedBlock(elseBlock);
        }
        
        //Set current block to post block
        func.setCurrBlock(postBlock);
    }
}
