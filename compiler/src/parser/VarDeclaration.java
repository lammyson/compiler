package parser;

import compiler.CMinusCompiler;
import java.io.FileWriter;
import java.io.IOException;
import lowlevel.CodeItem;
import lowlevel.Data;

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
            out.write("VarDecl " + identifier);
            
            if (num > 0) {
                out.write("[" + num + "]");
            }
            out.write("\n");
        }
        catch (IOException e) {
            System.out.println("Error writing to file in VarDeclaration");
        }
    }
    
    @Override
    public CodeItem genLLCode() {
        CMinusCompiler.globalHash.put(identifier, identifier);
        return new Data(1, identifier);
    }
}
