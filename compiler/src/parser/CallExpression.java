package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import lowlevel.Attribute;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operation;

/**
 * This class defines the call part of a var call which is a function call
 * @author Andrew
 */
public class CallExpression extends Expression {
    
    /**
     * This variable holds the id for the call
     */
    private String                id;
    
    /**
     * This variable holds the args-list inside the call
     */
    private ArrayList<Expression> argList;
    
    /**
     * Constructor
     * @param id
     * @param argList 
     */
    public CallExpression(String id, ArrayList<Expression> argList) {
        this.id = id;
        this.argList = argList;
    }
    
    /**
     * This method prints out the attributes of a function call
     * @param out 
     * @param indent 
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("CallExpression " + id + '\n');
            
            if (argList != null) {
                for (int i = 0; i < argList.size(); i++) {
                    argList.get(i).printMe(out, indent + 1, argList.get(i));
                }
            }
            
        }
        catch (IOException e) {
            System.out.println("Error writing to file in CallExpression");
        } 
    }
    
    @Override
    public void genLLCode(Function func) {
        Operation callOper = new Operation(Operation.OperationType.CALL, func.getCurrBlock());
        if (argList != null && !argList.isEmpty()) {
            parseParams(func, 0);
            callOper.addAttribute(new Attribute("numParams", Integer.toString(argList.size())));
        } else {
            callOper.addAttribute(new Attribute("numParams", Integer.toString(0)));
        }
      
        Operand src = new Operand(Operand.OperandType.STRING, id);
        callOper.setSrcOperand(0, src);
        func.getCurrBlock().appendOper(callOper);
        
        Operation retOper = new Operation(Operation.OperationType.ASSIGN, func.getCurrBlock());
        Integer retRegNum = func.getNewRegNum();
        Operand retSrc = new Operand(Operand.OperandType.MACRO, "RetReg");
        Operand retDest = new Operand(Operand.OperandType.REGISTER, retRegNum);
        retOper.setSrcOperand(0, retSrc);
        retOper.setDestOperand(0, retDest);
        func.getCurrBlock().appendOper(retOper);
        this.setRegisterNum(retRegNum);
    }
    
    private void parseParams(Function func, int paramNum) {
        if (argList.size() > paramNum) {
            Expression param = argList.get(paramNum);
            param.genLLCode(func);
            int currentParamNum = paramNum;
            parseParams(func, ++paramNum);
            Operation oper = new Operation(Operation.OperationType.PASS, func.getCurrBlock());
            oper.addAttribute(new Attribute("PARAM_NUM", Integer.toString(currentParamNum)));
            Operand src = new Operand(Operand.OperandType.REGISTER, param.getRegisterNum());
            oper.setSrcOperand(0, src);
            func.getCurrBlock().appendOper(oper);
        }
    }
}
