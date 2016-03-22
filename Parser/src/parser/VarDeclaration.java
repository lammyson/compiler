package parser;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class defines a Var-Declaration in the C Minus language
 * Variables are always of type int
 * @author Ryan
 */
public class VarDeclaration extends Declaration {
    
    /**
     * This variable holds the name of the variable
     */
    private String identifier;
    
    /**
     * This variable holds the size of the array
     * If -1, it is not of type array
     */
    private int    num;
    
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
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("  VarDecl " + identifier + " " + num + '\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in VarDeclaration");
        }
    }
}
