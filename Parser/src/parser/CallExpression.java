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
            for (int i = 0; i < argList.size(); i++) {
                Expression expr = argList.get(i);
                if (expr instanceof AssignExpression) {
                    ((AssignExpression) expr).printMe(out, indent+1);
                } else if (expr instanceof BinaryExpression) {
                    ((BinaryExpression) expr).printMe(out, indent+1);
                } else if (expr instanceof CallExpression) {
                    ((CallExpression) expr).printMe(out, indent+1);
                } else if (expr instanceof NumExpression) {
                    ((NumExpression) expr).printMe(out, indent+1);
                } else if (expr instanceof VarExpression) {
                    ((VarExpression) expr).printMe(out, indent+1);
                }
                out.write('\n');
            }
        }
        catch (IOException e) {
            System.out.println("Error writing to file in CompoundStatement");
        } 
    }
}
