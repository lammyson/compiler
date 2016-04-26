package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import lowlevel.*;

/**
 * This class is the root for an abstract syntax tree
 * @author Ryan
 */
public class Program {
    /**
     * This variable holds the abstract syntax tree for a program
     */
    private ArrayList<Declaration> program;
    
    /**
     * Constructor
     * @param declList 
     */
    public Program(ArrayList<Declaration> declList) {
        program = declList;
    }
    
    /**
     * This method will print the attributes of Program
     * @param out
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("Program\n");
            
            for (int i = 0; i < program.size(); i++) {
                program.get(i).printMe(out, indent+1, program.get(i));
            }
        }
        catch (IOException e) {
            System.out.println("Error writing to file in Program");
        }
    }
    
    public CodeItem genLLCode() {
        CodeItem item = null;
        for (int i = 0; i < program.size(); i++) {
            CodeItem nextItem = program.get(i).genLLCode();
            if (item == null) {
                item = nextItem;
            } else {
                item.setNextItem(nextItem);
            }
        }
        return item;
    }
}
