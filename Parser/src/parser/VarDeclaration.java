package parser;

/**
 * This class defines a Var-Declaration in the C Minus language
 * Variables are always of type int
 * @author Ryan
 */
public class VarDeclaration extends Declaration {
    
    /**
     * This variable holds the name of the variable
     */
    private IdExpression      identifier;
    
    /**
     * This variable holds the array of the variable
     * It will be null if it is not of type array
     */
    private VarCallExpression variable;
    
    /**
     * Constructor
     * @param id
     * @param var 
     */
    public VarDeclaration(IdExpression id, VarCallExpression var) {
        identifier = id;
        variable   = var;
    }
    
    /**
     * This will print out the attributes of this class
     */
    private void printMe() {
        
    }
}
