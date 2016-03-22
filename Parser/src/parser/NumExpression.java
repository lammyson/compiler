package parser;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is for the NUM terminal in the C Minus language
 * @author Ryan
 */
public class NumExpression extends Expression {
    /**
     * This variable holds the string name of the ID
     */
    private int num;
    
    /**
     * Constructor
     * @param n
     */
    public NumExpression(int n) {
        num = n;
    }
    
    /**
     * This method will print the attributes of IdExpression
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("NumExpression " + num + '\n');
        }
        catch (IOException e) {
            System.out.println("Error writing to file in CompoundStatement");
        }
    }
}
