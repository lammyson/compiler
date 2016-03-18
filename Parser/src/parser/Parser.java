package parser;

import java.util.ArrayList;
import scanner.CMinusScanner;
/**
 *
 * @author Ryan
 */
public class Parser {
    CMinusScanner scan;
    Program       p;
    
    public Parser(CMinusScanner s) {
        scan = s;
    }
    
    /**
     * 
     * @return 
     */
    public Program parseProgram() {
        ArrayList<Declaration> declList = new ArrayList();
        
        declList.add(parseDeclaration());
        
        while() {
            declList.add(parseDeclaration());
        }
        
        return new Program(declList);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
