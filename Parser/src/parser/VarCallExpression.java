package parser;

import java.util.ArrayList;

/**
 * This class combines the var and call nonterminals in the C Minus language
 * @author Ryan
 */
public class VarCallExpression extends Expression {
    
    /**
     * This variable holds the ID of the var or call
     */
    private IdExpression          identifier;
    
    /**
     * This variable holds an expression for an array var
     */
    private Expression            expression;
    
    /**
     * This variable holds the args for a call. Can be null.
     */
    private ArrayList<Expression> argsList;
    
    /**
     * This variable tells if it is a var or a call
     * 0 for var. 1 for call.
     */
    private int                   varOrCall;
    
    /**
     * Constructor
     * @param id
     * @param expr
     * @param args 
     * @param varcall 
     */
    public VarCallExpression(IdExpression id, Expression expr, 
                             ArrayList<Expression> args, int varcall) {
        identifier = id;
        expression = expr;
        argsList   = args;
        varOrCall  = varcall;
    }
    
    /**
     * This method will print the attributes of VarCallExpression
     */
    private void printMe() {
        
    }
}
