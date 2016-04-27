package parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import lowlevel.BasicBlock;
import lowlevel.Function;

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
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("CompoundStatement" + '\n');
            for (int i = 0; i < varDeclarations.size(); i++) {
                varDeclarations.get(i).printMe(out, indent+1);
            }
            for (int i = 0; i < statements.size(); i++) {
                statements.get(i).printMe(out, indent+1, statements.get(i));
            }
        }
        catch (IOException e) {
            System.out.println("Error writing to file in CompoundStatement");
        }
    }
    
    public void genLLCode(Function func) {
        for (VarDeclaration var : varDeclarations) {
            func.getTable().put(var.getIdentifier(), func.getNewRegNum());
        }
        for (Statement stmt : statements) {
            stmt.genLLCode(func);
        }
    }
}
