package parser;

import compiler.CMinusCompiler;
import java.io.FileWriter;
import java.io.IOException;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/**
 * This class encapsulates variables in the C Minus language
 * @author Ryan
 */
public class VarExpression extends Expression {
    
    /**
     * This variable holds the ID of the var 
     */
    private String     identifier;
    
    /**
     * This variable holds an expression for an array var. Can be null.
     */
    private Expression expression;
    
    /**
     * Constructor
     * @param id
     * @param expr
     */
    public VarExpression(String id, Expression expr) {
        identifier = id;
        expression = expr;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
    
    
    /**
     * This method will print the attributes of VarExpression
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("VarExpression " + identifier + '\n');
            
            if (expression != null) {
                expression.printMe(out, indent+1, expression);
            }
            
        }
        catch (IOException e) {
            System.out.println("Error writing to file in VarExpression");
        } 
    }
    
    /**
     * This method checks if the variable is found in the either symbol table
     * Throws LowLevelException if variable not found in symbol table
     * @param func 
     */
    @Override
    public void genLLCode(Function func) {
        
        if (func.getTable().containsKey(identifier)) {
            this.setRegisterNum(func.getTable().get(identifier));
        } else if (CMinusCompiler.globalHash.containsKey(identifier)) {
            Operation oper = new Operation(Operation.OperationType.LOAD_I, func.getCurrBlock());
            Integer newRegNum = func.getNewRegNum();
            Operand reg = new Operand(Operand.OperandType.REGISTER, newRegNum);
            Operand var = new Operand(Operand.OperandType.STRING, identifier);
            Operand offset = new Operand(Operand.OperandType.INTEGER, 0);
            oper.setSrcOperand(0, var);
            oper.setSrcOperand(1, offset);
            oper.setDestOperand(0, reg);
            func.getCurrBlock().appendOper(oper);
            this.setRegisterNum(newRegNum);
            func.getTable().put(identifier, newRegNum);
        } else {
            throw new CodeGenerationException("Variable does not exist");
        }
        
    }
}
