package parser;

import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
public class Program {
    private ArrayList<Declaration> program;
    
    public Program(ArrayList<Declaration> declList) {
        program = declList;
    }
    
    public Program parseProgram() {
        ArrayList<Declaration> declList = new ArrayList();
        
        
        return new Program(declList);
    }
}
