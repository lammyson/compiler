package parser;

import java.io.FileWriter;
import java.io.IOException;
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
}
