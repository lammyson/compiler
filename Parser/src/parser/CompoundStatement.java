package parser;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 * This class defines a compound-stmt in the C Minus language
 * @author Ryan
 */
public class CompoundStatement extends Statement {
    
    /**
     * This variable holds a list of variable declarations. Can be null
     */
    private ArrayList<VarDeclaration> varDeclarations;
    
    /**
     * This variable holds a list of statements. Can be null
     */
    private ArrayList<Statement>      statements;
    
    /**
     * Constructor
     * @param decls
     * @param stmts 
     */
    public CompoundStatement(ArrayList<VarDeclaration> decls,
                             ArrayList<Statement> stmts) {
        varDeclarations = decls;
        statements      = stmts;
    }
    
    /**
     * This method will print the attributes of CompoundStatement
     */
    public void printMe(FileWriter out) {
        
    }
}
