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
    private String  identifier;
    
    /**
     * This variable holds the size of the array
     * If -1, it is not of type array
     */
    private int num;
    
    /**
     * Constructor
     * @param id
     * @param n 
     */
    public VarDeclaration(String id, int n) {
        identifier = id;
        num        = n;
    }
    
    /**
     * This will print out the attributes of this class
     */
    private void printMe() {
        
    }
}
