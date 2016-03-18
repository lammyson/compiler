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
    private IdExpression  identifier;
    
    /**
     * This variable holds the size of the array
     * It will be null if it is not of type array
     */
    private NumExpression num;
    
    /**
     * Constructor
     * @param id
     * @param n 
     */
    public VarDeclaration(IdExpression id, NumExpression n) {
        identifier = id;
        num        = n;
    }
    
    /**
     * This will print out the attributes of this class
     */
    private void printMe() {
        
    }
}
