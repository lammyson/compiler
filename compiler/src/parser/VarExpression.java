package parser;

import compiler.CMinusCompiler;
import java.io.FileWriter;
import java.io.IOException;
import lowlevel.Function;

/**
 * This class encapsulates variables in the C Minus language
 * @author Ryan
 */
public class VarExpression extends Expression {
    
    /**
     * This variable holds the ID of the var 
     */
    private String     identifier;
    
    /**
     * This variable holds an expression for an array var. Can be null.
     */
    private Expression expression;
    
    /**
     * Constructor
     * @param id
     * @param expr
     */
    public VarExpression(String id, Expression expr) {
        identifier = id;
        expression = expr;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
    
    
    /**
     * This method will print the attributes of VarExpression
     * @param out
     * @param indent
     */
    public void printMe(FileWriter out, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                out.write("  ");
            }
            out.write("VarExpression " + identifier + '\n');
            
            if (expression != null) {
                expression.printMe(out, indent+1, expression);
            }
            
        }
        catch (IOException e) {
            System.out.println("Error writing to file in VarExpression");
        } 
    }
    
    /**
     * This method checks if the variable is found in the either symbol table
     * Throws LowLevelException if variable not found in symbol table
     * @param func 
     */
    public void genLLCode(Function func) {
        if (!func.getTable().containsValue(identifier) &&
            !CMinusCompiler.globalHash.containsValue(identifier)) {
            throw new CodeGenerationException("Variable does not exist");
        }
        maybe have to load if global and set to new register
    }
}
