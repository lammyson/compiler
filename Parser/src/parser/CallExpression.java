package parser;

import java.io.FileWriter;
import java.util.ArrayList;

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
     */
    public void printMe(FileWriter out) {
        
    }
}
