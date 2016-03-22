package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
    public void printMe(FileWriter out) {
        try {
            out.write("program\n");
            for (int i = 0; i < program.size(); i++) {
                Declaration decl = program.get(i);
                out.write("  ");
                if (decl instanceof VarDeclaration) {
                    ((VarDeclaration) decl).printMe(out, 1);
                } else if (decl instanceof FunDeclaration) {
                    ((FunDeclaration) decl).printMe(out, 1);
                }
                out.write('\n');
            }
        }
        catch (IOException e) {
            System.out.println("Error writing to file in Program");
        }
    }
}
