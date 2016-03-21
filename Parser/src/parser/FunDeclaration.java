package parser;

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
    private String      identifier;
    
    /**
     * This variable holds the parameters that the function needs
     */
    private ArrayList<Param>  paramList;
    
    /**
     * This variable holds the function definition
     */
    private CompoundStatement compoundStatement;
    
    /**
     * Constructor
     * @param ret
     * @param id
     * @param paramList
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
     */
    private void printMe() {
        
    }
}
