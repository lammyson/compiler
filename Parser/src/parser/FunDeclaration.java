package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
            
            for (int i = 0; i < paramList.size(); i++) {
                paramList.get(i).printMe(out, indent+1);
                out.write('\n');
            }
            
            compoundStatement.printMe(out, indent+1);
            out.write('\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in FunDeclaration");
        }
    }
}
