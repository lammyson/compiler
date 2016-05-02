package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import lowlevel.BasicBlock;
import lowlevel.CodeItem;
import lowlevel.FuncParam;
import lowlevel.Function;

/**
 * This class defines a function declaration of the C Minus language
 * @author Ryan
 */
public class FunDeclaration extends Declaration {
    
    /**
     * This variable holds the return type of the function.
     * 0 is void. 1 is int.
     */
    private int               returnType;
    
    /**
     * This variable holds the identifier of the function. ie it's name.
     */
    private String            identifier;
    
    /**
     * This variable holds the parameters that the function needs
     */
    private ArrayList<Param>  paramList;
    
    /**
     * This variable holds the function definition. Can be null.
     */
    private CompoundStatement compoundStatement;
    
    /**
     * Constructor
     * @param ret
     * @param id
     * @param params
     * @param compound 
     */
    public FunDeclaration(int ret, String id, ArrayList<Param> params,
                          CompoundStatement compound) {
        returnType        = ret;
        identifier        = id;
        paramList         = params;
        compoundStatement = compound;
    }
    
    /**
     * This method will print the attributes of this class
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("FunDecl ");
            
            if (returnType == 0) {
                out.write("void ");
            } else if (returnType == 1) {
                out.write("int ");
            }
            out.write(identifier + '\n');
            
            if (paramList != null) {
                for (int i = 0; i < paramList.size(); i++) {
                    paramList.get(i).printMe(out, indent+1);
                }
            }
            
            compoundStatement.printMe(out, indent+1);
            
        }
        catch (IOException e) {
            System.out.println("Error writing to file in FunDeclaration");
        }
    }
    
    @Override
    public CodeItem genLLCode() {
        Function func = new Function(returnType, identifier);
        FuncParam firstParam = null;
        FuncParam lastParam = null;
        FuncParam currentParam = null;
        if (paramList != null && !paramList.isEmpty()) {
            for (Param param : paramList) {
                func.getTable().put(param.getIdentifier(), func.getNewRegNum());
                currentParam = param.genLLCode();
                if (firstParam == null) {
                    firstParam = currentParam;
                    lastParam = firstParam;
                    func.setFirstParam(firstParam);
                } else {
                    lastParam.setNextParam(currentParam);
                    lastParam = currentParam;
                }
            }
        }
        func.createBlock0();
        BasicBlock cmpdStmt = new BasicBlock(func);
        func.setCurrBlock(cmpdStmt);
        func.appendBlock(cmpdStmt);
        compoundStatement.genLLCode(func);
        func.appendBlock(func.genReturnBlock());
        if (func.getFirstUnconnectedBlock() != null) {
            func.appendBlock(func.getFirstUnconnectedBlock());
        }
        
        return func;
    }
}
