package parser;

/**
 * This class defines an Assign Expression in the C Minus language
 * It is of the form var = expression;
 * @author Ryan
 */
public class AssignExpression extends Expression {
    /**
     * This holds the var that will take the expression value
     */
    private VarExpression var;
    
    /**
     * This holds the expression that will be assigned into the var
     */
    private Expression expression;
    
    /**
     * Construction
     * @param v
     * @param expr 
     */
    public AssignExpression(VarExpression v, Expression expr) {
        var        = v;
        expression = expr;
    }
    
    /**
     * This method prints the attributes of AssignExpression
     */
    private void printMe() {
        
    }
}
