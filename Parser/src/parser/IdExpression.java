package parser;

/**
 * This class defines an IdExpression in the C Minus language. It is the
 * ID terminal
 * @author Ryan
 */
public class IdExpression extends Expression {
        
    /**
     * This variable holds the string name of the ID
     */
    private String  identifier;
    
    /**
     * Constructor
     * @param id
     */
    public IdExpression(String id) {
        identifier = id;
    }
    
    /**
     * This method will print the attributes of IdExpression
     */
    private void printMe() {
        
    }
}
