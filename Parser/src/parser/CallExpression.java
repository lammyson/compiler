/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parser;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Andrew
 */
public class CallExpression extends Expression {
    
    String id;
    
    ArrayList<Expression> argList;
    
    public CallExpression(String id, ArrayList<Expression> argList) {
        this.id = id;
        this.argList = argList;
    }
    
    public void printMe(FileWriter out) {
        
    }
    
}
