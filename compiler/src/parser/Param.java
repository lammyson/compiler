package parser;

import java.io.FileWriter;
import java.io.IOException;
import lowlevel.FuncParam;

/**
 * This class defines the params nonterminal in the C Minus language
 * It will always be of type int
 * Param is found in function declarations
 * @author Ryan
 */
public class Param {
    
    /**
     * This variable holds the id of the param
     */
    private String  identifier;
    
    /**
     * This variable tells if the param is of type array
     * False if not array. true if array.
     */
    private boolean arrayType;
    
    /**
     * Constructor
     * @param id
     * @param array 
     */
    public Param(String id, boolean array) {
        identifier = id;
        arrayType  = array;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean isArrayType() {
        return arrayType;
    }

    public void setArrayType(boolean arrayType) {
        this.arrayType = arrayType;
    }
    
    
    /**
     * This method will print the attributes of a param
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("Param " + identifier);
            
            if (arrayType) {
                out.write("[]");
            }
            out.write('\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in Param");
        }
    }
    
    public FuncParam genLLCode() {
        
    }
}
