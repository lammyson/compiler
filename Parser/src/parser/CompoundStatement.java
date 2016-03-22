package parser;

import java.io.FileWriter;
import java.io.IOException;
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
                out.write('\n');
            }
            for (int i = 0; i < statements.size(); i++) {
                Statement stmt = statements.get(i);
                if (stmt instanceof CompoundStatement) {
                    ((CompoundStatement) stmt).printMe(out, indent+1);
                } else if (stmt instanceof ExpressionStatement) {
                    ((ExpressionStatement) stmt).printMe(out, indent+1);
                } else if (stmt instanceof IterationStatement) {
                    ((IterationStatement) stmt).printMe(out, indent+1);
                } else if (stmt instanceof ReturnStatement) {
                    ((ReturnStatement) stmt).printMe(out, indent+1);
                } else if (stmt instanceof SelectionStatement) {
                    ((SelectionStatement) stmt).printMe(out, indent+1);
                }
                out.write('\n');
            }
        }
        catch (IOException e) {
            System.out.println("Error writing to file in CompoundStatement");
        }
    }
}
