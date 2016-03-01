package parser;

import java.util.ArrayList;

/**
 *
 * @author Ryan
 */
public class FunDeclaration extends Declaration {
    private ArrayList<Param> paramList;
    private Statement        compoundStatement;
    private Statement        returnStatement;
    private Expression       id;
}
