package parser;

import java.util.ArrayList;

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
     */
    private void printMe() {
        
    }
}
