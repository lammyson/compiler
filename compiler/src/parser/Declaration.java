package parser;

import java.io.FileWriter;
import lowlevel.CodeItem;

/**
 * This class acts as a parent class for VarDeclaration and FunDeclaration
 * @author Ryan
 */
public abstract class Declaration {
    /**
     * Helper function for printMe with Declarations
     * @param out
     * @param indent
     * @param decl 
     */
    public void printMe(FileWriter out, int indent, Declaration decl) {
        if (decl instanceof VarDeclaration) {
            ((VarDeclaration) decl).printMe(out, indent);
        } else if (decl instanceof FunDeclaration) {
            ((FunDeclaration) decl).printMe(out, indent);
        }
    }
    
    public abstract CodeItem genLLCode();
    
}
